package com.giovanijfc.sistemadepostagens.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Amizade;
import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.Topico;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.repository.AmizadeRepository;
import com.giovanijfc.sistemadepostagens.repository.PostagemRepository;
import com.giovanijfc.sistemadepostagens.repository.TopicoRepository;
import com.giovanijfc.sistemadepostagens.repository.UsuarioRepository;

@Service
public class TopicoService {

	@Autowired
	private PostagemRepository postagemRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private AmizadeRepository amizadeRepo;
	@Autowired
	private TopicoRepository topicoRepo;

	public Topico buscar(Integer idTopico) {
		return topicoRepo.findById(idTopico).orElse(null);
	}

	public List<Topico> buscarTodosPostSeu(Integer idUser) {
		List<Topico> listaTopicos = topicoRepo.findPost(idUser);
		listaTopicos = listaTopicos.stream().filter(t -> t.getPostPrincipal().getUsuario().getId() == idUser)
				.collect(Collectors.toList());
		return listaTopicos;
	}

	public List<Topico> buscarTodosAmizadeESeus(Integer idUser) {
		List<Amizade> listaAmizade = amizadeRepo.findAmigos(idUser);
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Postagem> listaPostagem = new ArrayList<Postagem>();
		List<Topico> listaTopico = new ArrayList<Topico>();
		List<Topico> listaTopicoFinal = new ArrayList<Topico>();
		listaAmizade.stream().forEach(x -> listaUsuarios
				.add(new Usuario(usuarioRepo.findById(x.getUsuarioSecundario().getId()).orElse(null))));
		listaUsuarios.forEach(x -> listaPostagem.addAll(postagemRepo.findPost(x.getId())));
		listaPostagem.addAll(postagemRepo.findPost(idUser));
		listaPostagem.forEach(p -> listaTopico.add(topicoRepo.findByPostPrincipal(p)));
		listaTopicoFinal = listaTopico.stream().sorted((topico1, topico2) ->
		{ return topico2.getPostPrincipal().getData().compareTo(topico1.getPostPrincipal().getData());
		}).collect(Collectors.toList());
		
		return listaTopicoFinal;
	}
}
