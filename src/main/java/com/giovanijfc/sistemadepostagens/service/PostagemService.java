package com.giovanijfc.sistemadepostagens.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.PostAndTopico;
import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.Resposta;
import com.giovanijfc.sistemadepostagens.domain.Topico;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.domain.enums.TipoPostagem;
import com.giovanijfc.sistemadepostagens.repository.PostagemRepository;
import com.giovanijfc.sistemadepostagens.repository.RespostaRepository;
import com.giovanijfc.sistemadepostagens.repository.TopicoRepository;
import com.giovanijfc.sistemadepostagens.repository.UsuarioRepository;
import com.giovanijfc.sistemadepostagens.service.exceptions.DataIntegrityException;
import com.giovanijfc.sistemadepostagens.service.exceptions.ObjectNotFoundException;

@Service
public class PostagemService {

	@Autowired
	private TopicoRepository topicoRepo;
	@Autowired
	private PostagemRepository postagemRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private RespostaRepository respostaRepo;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss");

	public Postagem buscarPorIdPost(Integer id) {
		Postagem obj = postagemRepo.findById(id).orElse(null);
		if (obj == null) {
			throw new ObjectNotFoundException("Postagem não foi encontrada!");
		}
		return obj;
	}

	public List<Postagem> buscarTodos() {
		List<Postagem> list = postagemRepo.findAll();
		return list;
	}

	public PostAndTopico add(String postagem, Integer id) {
		if (postagem == null || postagem.equals("")) {
			throw new DataIntegrityException("Dados da postagem incorreta, tente novamente!");
		} else {
			Usuario u1 = usuarioRepo.findById(id).orElse(null);
			Topico topico = new Topico(null);
			Postagem postagem1 = new Postagem(null, postagem,(new Date(System.currentTimeMillis())), u1, TipoPostagem.POSTAGEM);
			PostAndTopico tp1 = new PostAndTopico(postagem1, topico);
			return tp1;
		}
	}

	public Postagem adicionar(String postagem, Integer id) {
		Postagem post = add(postagem, id).getPost();
		Topico topico = add(postagem, id).getTopico();
		topicoRepo.save(topico);
		postagemRepo.save(post);
		topico.setPostPrincipal(post);
		topicoRepo.flush();
		return post;
	}

	public void deletePostagem(Integer idTopico) {
		Postagem postagem = topicoRepo.getOne(idTopico).getPostPrincipal();
		postagemRepo.delete(postagem);
		topicoRepo.deleteById(idTopico);
		topicoRepo.flush();
		postagemRepo.flush();
	}

	public void deleteResposta(Integer id) {
		respostaRepo.deleteById(id);
		respostaRepo.flush();
	}

	public void atualizar(String texto, Integer id) {
		Postagem postagem = postagemRepo.findById(id).orElse(null);
		if (!texto.equals("")  && postagem != null) {
			postagem.setTexto(texto);
			postagemRepo.flush();
		} else {
			throw new DataIntegrityException("Não foi possivel editar essa postagem, tente novamente!");
		}
	}

	public Resposta addResposta(Resposta resposta, Integer id) {
		Usuario u1 = usuarioRepo.findById(id).orElse(null);
		Resposta resposta1 = new Resposta(null, resposta.getTexto(), new Date(System.currentTimeMillis()), u1, TipoPostagem.RESPOSTA);
		return resposta1;
	}

	public Resposta adicionarResposta(Resposta resposta, Integer idUser, Integer IdPost) {
		Postagem postP = postagemRepo.findById(IdPost).orElse(null);
		Resposta resposta1 = addResposta(resposta, idUser);
		if (resposta1.getTexto().equals(null) || resposta1.getTexto().equals("") || postP.getTipo() == TipoPostagem.RESPOSTA) {
			throw new DataIntegrityException("Não foi possivel adicionar a resposta, tente novamente!");
		} else {
			respostaRepo.save(resposta1);
			postP.getResposta().add(resposta1);
			postagemRepo.flush();
		}
		return resposta1;
	}

	public void atualizarResposta(String texto, Integer idResp) {
		Resposta p1 = respostaRepo.findById(idResp).orElse(null);
		if (!texto.equals(null) || !texto.equals("") && p1 != null) {
			p1.setTexto(texto);
			postagemRepo.flush();
		} else {
			throw new DataIntegrityException("Não foi possivel editar a respostam, tente novamente!");
		}
	}
}
