package com.giovanijfc.sistemadepostagens.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;
import com.giovanijfc.sistemadepostagens.dto.UsuarioDTO;
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

	public Usuario buscarPorEmail(String email) {
		Usuario obj = usuarioRepo.findByEmail(email);
		if (obj == null) {
			System.out.println("Usuario não encontrado!");
		}
		return obj;
	}

	public Usuario add(UsuarioDTO objDto) {
		return new Usuario(null, objDto.getNome(), objDto.getDescricao(), objDto.getEmail(), objDto.getSenha(), Cargo.USUÁRIO,
				new Date(System.currentTimeMillis()));
	}

	public Usuario adicionar(UsuarioDTO objDto) {
		Usuario user = add(objDto);
		Membro membro = membroSer.add(objDto.getNome(), objDto.getEmail());
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

	public void atualizarDTO(UsuarioDTO objDto, Usuario obj) {
		Membro membro = membroRepo.findByEmail(obj.getEmail());

		if (objDto.getNome() != null || obj.getNome() != "") {
			obj.setNome(objDto.getNome());
			membro.setNome(objDto.getNome());
		}
		if (objDto.getEmail() != null || obj.getEmail() != "") {
			obj.setEmail(objDto.getEmail());
			membro.setEmail(objDto.getEmail());
		}
		if (objDto.getSenha() != null || objDto.getSenha() != "") {
			obj.setSenha(objDto.getSenha());
		}
		if (objDto.getDescricao() != null || objDto.getDescricao() != "") {
			obj.setDescricao(objDto.getDescricao());
		}
		if (objDto.getUrlFotoPerfil() != null || objDto.getUrlFotoPerfil() != "") {
			obj.setUrlFotoPerfil(objDto.getUrlFotoPerfil());
		}
		usuarioRepo.flush();
		membroRepo.flush();
	}
}
