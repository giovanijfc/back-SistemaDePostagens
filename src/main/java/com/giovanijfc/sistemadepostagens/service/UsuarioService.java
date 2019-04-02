package com.giovanijfc.sistemadepostagens.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Amizade;
import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;
import com.giovanijfc.sistemadepostagens.dto.UsuarioDTO;
import com.giovanijfc.sistemadepostagens.repository.AmizadeRepository;
import com.giovanijfc.sistemadepostagens.repository.MembroRepository;
import com.giovanijfc.sistemadepostagens.repository.UsuarioRepository;
import com.giovanijfc.sistemadepostagens.security.UserSS;
import com.giovanijfc.sistemadepostagens.service.exceptions.DataIntegrityException;
import com.giovanijfc.sistemadepostagens.service.exceptions.ForbiddenException;
import com.giovanijfc.sistemadepostagens.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private MembroRepository membroRepo;
	@Autowired
	private MembroService membroSer;
	@Autowired
	private AmizadeRepository amizadeRepo;
	@Autowired
	private BCryptPasswordEncoder pe;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	public Usuario buscarPorEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user.getUsername() != email || user == null || !user.hasRole(Cargo.ADMINISTRADOR)) {
			throw new ForbiddenException("Acesso negado, não foi possivel continuar com essa requisição!");
		}
		Usuario usuario = usuarioRepo.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Usuario não encontrado!");
		}
		return usuario;
	}

	public Usuario add(UsuarioDTO usuarioDto) {
		return new Usuario(null, usuarioDto.getNome(), usuarioDto.getDescricao(), usuarioDto.getEmail(),
				pe.encode(usuarioDto.getSenha()), sdf.format(new Date(System.currentTimeMillis())), pe.encode(usuarioDto.getPalavraChave()));
	}

	public Usuario adicionar(UsuarioDTO usuarioDto) {
		Usuario user = add(usuarioDto);
		Membro membro = membroSer.add(usuarioDto.getNome(), usuarioDto.getEmail());
		if (user == null) {
			throw new DataIntegrityException("Dados incorretos, não foi possivel continuar!");
		} else {
			user.addPerfil(Cargo.USUÁRIO);
			usuarioRepo.save(user);
			membroRepo.save(membro);
		}
		return user;
	}

	public void delete(Integer id) {
		usuarioRepo.deleteById(id);
	}

	public void atualizarDTO(UsuarioDTO usuarioDto, Usuario usuario) {
		Membro membro = membroRepo.findByEmail(usuario.getEmail());

		if (usuarioDto.getNome() != null || usuarioDto.getNome() != "") {
			usuario.setNome(usuarioDto.getNome());
			membro.setNome(usuarioDto.getNome());
		}
		if (usuarioDto.getEmail() != null || usuarioDto.getEmail() != "") {
			usuario.setEmail(usuarioDto.getEmail());
			membro.setEmail(usuarioDto.getEmail());
		}
		if (usuarioDto.getSenha() != null || usuarioDto.getSenha() != "") {
			usuario.setSenha(usuarioDto.getSenha());
		}
		if (usuarioDto.getDescricao() != null || usuarioDto.getDescricao() != "") {
			usuario.setDescricao(usuarioDto.getDescricao());
		}
		if (usuarioDto.getUrlFotoPerfil() != null || usuarioDto.getUrlFotoPerfil() != "") {
			usuario.setUrlFotoPerfil(usuarioDto.getUrlFotoPerfil());
		}
		usuarioRepo.flush();
		membroRepo.flush();
	}

	public Usuario adicionarAmigo(Integer idUsuarioSecundario, Integer idUsuarioPrincipal) {
		Usuario userPrincipal = usuarioRepo.findById(idUsuarioPrincipal).orElse(null);
		Usuario userSecundario = usuarioRepo.findById(idUsuarioSecundario).orElse(null);
		List<Amizade> listaAmizades = usuarioRepo.findById(idUsuarioPrincipal).get().getAmizade().stream()
				.filter(x -> x.getUsuarioSecundario().getId() == userSecundario.getId()).collect(Collectors.toList());
		List<Amizade> listaAmizades2 = usuarioRepo.findById(idUsuarioSecundario).get().getAmizade().stream()
				.filter(x -> x.getUsuarioSecundario().getId() == userSecundario.getId()).collect(Collectors.toList());
		Amizade amizade = new Amizade(null, userPrincipal, userSecundario, sdf.format(new Date(System.currentTimeMillis())));
		Amizade amizade2 = new Amizade(null, userSecundario, userPrincipal, sdf.format(new Date(System.currentTimeMillis())));
		if ((userPrincipal != null && userSecundario != null) && (userPrincipal != userSecundario) && (amizade != null)
				&& (listaAmizades.isEmpty()) && (listaAmizades2.isEmpty())) {
			amizadeRepo.saveAll(Arrays.asList(amizade, amizade2));
			userPrincipal.getAmizade().add(amizade);
			userSecundario.getAmizade().add(amizade2);
			usuarioRepo.flush();
		} else {
			throw new ForbiddenException("******ERROR******");
		}
		return userPrincipal;
	}

	public Usuario removerAmigo(Integer idUsuarioSecundario, Integer idUsuarioPrincipal) {
		Usuario userPrincipal = usuarioRepo.findById(idUsuarioPrincipal).orElse(null);
		Usuario userSecundario = usuarioRepo.findById(idUsuarioSecundario).orElse(null);
		List<Amizade> listaAmizades = usuarioRepo.findById(idUsuarioPrincipal).get().getAmizade().stream()
				.filter(x -> x.getUsuarioSecundario().getId() == userSecundario.getId()).collect(Collectors.toList());
		List<Amizade> listaAmizades2 = usuarioRepo.findById(idUsuarioSecundario).get().getAmizade().stream()
				.filter(x -> x.getUsuarioSecundario().getId() == userPrincipal.getId()).collect(Collectors.toList());
		if ((userPrincipal != null && userSecundario != null) && (userPrincipal != userSecundario)
				&& (!listaAmizades.isEmpty()) && (!listaAmizades2.isEmpty())) {
			userPrincipal.getAmizade().remove(listaAmizades.get(0));
			userSecundario.getAmizade().remove(listaAmizades2.get(0));
			amizadeRepo.delete(listaAmizades.get(0));
			amizadeRepo.flush();
			usuarioRepo.flush();
		} else {
			throw new ForbiddenException("******ERROR******");
		}
		return userPrincipal;
	}
	public String changePass(String senha, Usuario user) {
		user.setSenha(pe.encode(senha));
		usuarioRepo.flush();
		return "Senha trocada com sucesso";
	}
}
