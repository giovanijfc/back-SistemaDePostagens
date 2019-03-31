package com.giovanijfc.sistemadepostagens.service;

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
	
	public Postagem buscarPorIdPost(Integer id) {
		Postagem obj = postagemRepo.findById(id).orElse(null);
		if (obj == null) {
			System.out.println("Postagem não encontrado!");
		}
		return obj;
	}
	public List<Postagem> buscarTodos(){
		List<Postagem> list= postagemRepo.findAll();
		return list;
	}

	public PostAndTopico add(Postagem postagem, Integer id) {
		Usuario u1 = usuarioRepo.findById(id).orElse(null);
		Topico topico = new Topico(null);
		Postagem postagem1 = new Postagem(null, postagem.getTexto(), new Date(System.currentTimeMillis()), u1, TipoPostagem.POSTAGEM);
		topicoRepo.save(topico);
		PostAndTopico tp1 = new PostAndTopico(postagem1, topico);
		return tp1;
	}

	public Postagem adicionar(Postagem postagem, Integer id) {
		Postagem post = add(postagem, id).getPost();
		Topico topico = add(postagem, id).getTopico();
		if (post.getTexto() == null || post.getTexto() == "") {
			System.out.println("Post incorretos!");
		} else {
			postagemRepo.save(post);
			topico.setPostPrincipal(post);
			topicoRepo.flush();
		}
		return post;
	}

	public void delete(Integer idTopico) {
		Postagem postagem = topicoRepo.getOne(idTopico).getPostPrincipal();
		postagemRepo.delete(postagem);
		topicoRepo.deleteById(idTopico);
		topicoRepo.flush();
		postagemRepo.flush();
	}

	public void atualizar(String texto, Integer id) {
		Postagem postagem = postagemRepo.findById(id).orElse(null);
		if(texto != null || texto != "") {
			postagem.setTexto(texto);
			postagemRepo.flush();
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
		if (resposta1.getTexto() == null || resposta1.getTexto() == "" || postP.getTipo() == TipoPostagem.RESPOSTA) {
			System.out.println("Post incorretos!");
		} else {
			respostaRepo.save(resposta1);
			postP.getResposta().add(resposta1);
			postagemRepo.flush();
		}
		return resposta1;
	}
	public void atualizarResposta(String texto, Integer idResp) {
		Resposta p1 = respostaRepo.findById(idResp).orElse(null);
		if(texto != null || texto != "") {
			p1.setTexto(texto);
			postagemRepo.flush();
		}
	}
}
