package com.giovanijfc.sistemadepostagens.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Amizade;
import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;
import com.giovanijfc.sistemadepostagens.dto.UsuarioDTO;
import com.giovanijfc.sistemadepostagens.repository.AmizadeRepository;
import com.giovanijfc.sistemadepostagens.repository.MembroRepository;
import com.giovanijfc.sistemadepostagens.repository.UsuarioRepository;

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

	public Usuario buscarPorEmail(String email) {
		Usuario usuario = usuarioRepo.findByEmail(email);
		if (usuario == null) {
			System.out.println("Usuario não encontrado!");
		}
		return usuario;
	}

	public Usuario add(UsuarioDTO usuarioDto) {
		return new Usuario(null, usuarioDto.getNome(), usuarioDto.getDescricao(), usuarioDto.getEmail(), usuarioDto.getSenha(),
				Cargo.USUÁRIO, new Date(System.currentTimeMillis()));
	}

	public Usuario adicionar(UsuarioDTO usuarioDto) {
		Usuario user = add(usuarioDto);
		Membro membro = membroSer.add(usuarioDto.getNome(), usuarioDto.getEmail());
		if (user == null) {
			System.out.println("Dados incorretos!");
		} else {
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
		List<Amizade> listaAmizades = usuarioRepo.findById(idUsuarioPrincipal).get().getAmizade().stream().filter(
				x -> x.getUsuarioSecundario().getId() == userSecundario.getId()).collect(Collectors.toList());
		Amizade amizade = new Amizade(null, userPrincipal, userSecundario);
		if ((userPrincipal != null && userSecundario != null) && (userPrincipal != userSecundario) && (amizade != null)
				&& (listaAmizades.isEmpty())) {
			amizadeRepo.save(amizade);
			userPrincipal.getAmizade().add(amizade);
			userSecundario.getAmizade().add(amizade);
			usuarioRepo.flush();
		} else {
			System.out.println("ERROR!");
		}
		return userPrincipal;
	}

	public Usuario removerAmigo(Integer idUsuarioSecundario, Integer idUsuarioPrincipal) {
		Usuario userPrincipal = usuarioRepo.findById(idUsuarioPrincipal).orElse(null);
		Usuario userSecundario = usuarioRepo.findById(idUsuarioSecundario).orElse(null);
		List<Amizade> listaAmizades = usuarioRepo.findById(idUsuarioPrincipal).get().getAmizade().stream().filter(
				x -> x.getUsuarioSecundario().getId() == userSecundario.getId()).collect(Collectors.toList());
		if ((userPrincipal != null && userSecundario != null) && (userPrincipal != userSecundario) && (listaAmizades.get(0)!= null)
				&& (!listaAmizades.isEmpty())) {
			userPrincipal.getAmizade().remove(listaAmizades.get(0));
			userSecundario.getAmizade().remove(listaAmizades.get(0));
			amizadeRepo.delete(listaAmizades.get(0));
			amizadeRepo.flush();
			usuarioRepo.flush();
		} else {
			System.out.println("ERROR!");
		}
		return userPrincipal;
	}
}
