package com.giovanijfc.sistemadepostagens.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.PostagemGrupo;
import com.giovanijfc.sistemadepostagens.domain.RespostaGroup;
import com.giovanijfc.sistemadepostagens.domain.TopicoGrupo;
import com.giovanijfc.sistemadepostagens.domain.enums.TipoPostagem;
import com.giovanijfc.sistemadepostagens.repository.GrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.MembroRepository;
import com.giovanijfc.sistemadepostagens.repository.PostagemGrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.RespostaGrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.TopicoGrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepo;
	@Autowired
	private PostagemGrupoRepository postagemRepo;
	@Autowired
	private TopicoGrupoRepository topicoRepo;
	@Autowired
	private MembroRepository membroRepo;
	@Autowired
	private RespostaGrupoRepository respostaRepo;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss");

	public Grupo buscarPorNome(String nome) {
		Grupo grupo = grupoRepo.findByNome(nome);
		if (grupo == null) {
			System.out.println("Grupo não encontrado!");
		}
		return grupo;
	}

	public Grupo add(Grupo grupo) {
		return new Grupo(null, grupo.getDescricao(), grupo.getUrlFotoGrupo(), grupo.getNome(),
				sdf.format(new Date(System.currentTimeMillis())));
	}

	public Grupo adicionar(Grupo grupo) {
		Grupo grupo1 = add(grupo);
		if (grupo1 == null) {
			System.out.println("Dados incorretos!");
		} else {
			grupoRepo.save(grupo1);
		}
		return grupo1;
	}

	public void delete(Integer id) {
		grupoRepo.deleteById(id);
	}

	public void atualizar(Grupo grupo, Integer idGrupo) {
		Grupo grupo1 = grupoRepo.findById(idGrupo).orElse(null);
		if (grupo.getDescricao() != null || grupo.getDescricao() != "") {
			grupo1.setDescricao(grupo.getDescricao());
		}
		if (grupo.getNome() != null || grupo.getNome() != "") {
			grupo1.setNome(grupo.getNome());
		}
		if (grupo.getUrlFotoGrupo() != null || grupo.getUrlFotoGrupo() != "") {
			grupo1.setUrlFotoGrupo(grupo.getUrlFotoGrupo());
		}
		grupoRepo.flush();
	}

	public TopicoGrupo add(PostagemGrupo postagem, Integer id, Grupo grupo) {
		Membro membro = membroRepo.findById(id).orElse(null);
		PostagemGrupo postagem1 = new PostagemGrupo(null, postagem.getTexto(),
				sdf.format(new Date(System.currentTimeMillis())), sdff.format(new Date(System.currentTimeMillis())),
				membro, TipoPostagem.POSTAGEM);
		List<Membro> membros = grupo.getMembro().stream().filter(x -> x == membro).collect(Collectors.toList());
		if (postagem1.getTexto() != null && postagem1.getTexto() != "" && !membros.isEmpty()) {
			TopicoGrupo topico = new TopicoGrupo(null);
			postagemRepo.save(postagem1);
			topico.setPostPrincipal(postagem1);
			topicoRepo.save(topico);
			return topico;
		}
		return null;
	}

	public TopicoGrupo adicionar(PostagemGrupo postagem, Integer idUser, Grupo grupo) {
		TopicoGrupo topico = add(postagem, idUser, grupo);
		if (topico != null) {
			return topico;
		}
		return null;
	}

	public void adicionarPostagem(PostagemGrupo postagem, Integer idUser, Integer idGrupo) {
		Grupo grupo = grupoRepo.findById(idGrupo).orElse(null);
		TopicoGrupo topico = adicionar(postagem, idUser, grupo);
		if (topico != null) {
			grupo.getTopicos().add(topico);
			grupoRepo.flush();
		} else {
			System.out.println("Não foi possivel postar");
		}
	}

	public RespostaGroup addResposta(RespostaGroup respostaGroup, Integer id, Grupo grupo) {
		Membro membro = membroRepo.findById(id).orElse(null);
		List<Membro> membros = grupo.getMembro().stream().filter(x -> x == membro).collect(Collectors.toList());
		if (!membros.isEmpty()) {
			RespostaGroup resposta1 = new RespostaGroup(null, respostaGroup.getTexto(),
					sdf.format(new Date(System.currentTimeMillis())), sdff.format(new Date(System.currentTimeMillis())),
					membro, TipoPostagem.RESPOSTA);
			return resposta1;
		} else {
			return null;
		}
	}

	public RespostaGroup adicionarResposta(RespostaGroup respostaGroup, Integer idMember, Integer idPost,
			Integer idGroup) {
		Grupo grupo = grupoRepo.findById(idGroup).orElse(null);
		PostagemGrupo postP = postagemRepo.findById(idPost).orElse(null);
		RespostaGroup resposta1 = addResposta(respostaGroup, idMember, grupo);
		if (resposta1 == null) {
			System.out.println("Não faz parte do grupo");
		} else if (resposta1.getTexto() == null || resposta1.getTexto() == "") {
			System.out.println("post incorreto");
		} else {
			respostaRepo.save(resposta1);
			postP.getResposta().add(resposta1);
			postagemRepo.flush();
		}
		return resposta1;
	}
	public void deletePostagem(Integer idTopico) {
		PostagemGrupo postagem = topicoRepo.getOne(idTopico).getPostPrincipal();
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
		PostagemGrupo postagem = postagemRepo.findById(id).orElse(null);
		if (texto != null || texto != "") {
			postagem.setTexto(texto);
			postagemRepo.flush();
		}
	}
	public void atualizarResposta(String texto, Integer idResp) {
		RespostaGroup p1 = respostaRepo.findById(idResp).orElse(null);
		if (texto != null || texto != "") {
			p1.setTexto(texto);
			postagemRepo.flush();
		}
	}
}
