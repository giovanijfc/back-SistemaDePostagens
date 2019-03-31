package com.giovanijfc.sistemadepostagens.service;

import java.util.Date;

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

	public Grupo buscarPorNome(String nome) {
		Grupo grupo = grupoRepo.findByNome(nome);
		if (grupo == null) {
			System.out.println("Grupo não encontrado!");
		}
		return grupo;
	}

	public Grupo add(Grupo grupo) {
		return new Grupo(null, grupo.getDescricao(), grupo.getUrlFotoGrupo(), grupo.getNome(),
				new Date(System.currentTimeMillis()));
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

	public TopicoGrupo add(PostagemGrupo postagem, Integer id) {
		Membro membro = membroRepo.findById(id).orElse(null);
		TopicoGrupo topico = new TopicoGrupo(null);
		PostagemGrupo postagem1 = new PostagemGrupo(null, postagem.getTexto(), new Date(System.currentTimeMillis()),
				membro, TipoPostagem.POSTAGEM);
		if (postagem1.getTexto() != null || postagem1.getTexto() != "") {
			postagemRepo.save(postagem1);
			topico.setPostPrincipal(postagem1);
			topicoRepo.save(topico);
			return topico;
		}

		return null;
	}

	public TopicoGrupo adicionar(PostagemGrupo postagem, Integer idUser) {
		;
		TopicoGrupo topico = add(postagem, idUser);
		return topico;
	}

	public void adicionarPostagem(PostagemGrupo postagem, Integer idUser, Integer idGrupo) {
		TopicoGrupo topico = adicionar(postagem, idUser);
		Grupo grupo = grupoRepo.findById(idGrupo).orElse(null);
		grupo.getTopicos().add(topico);
		grupoRepo.flush();
	}

	public RespostaGroup addResposta(RespostaGroup respostaGroup, Integer id) {
		Membro m1 = membroRepo.findById(id).orElse(null);
		RespostaGroup resposta1 = new RespostaGroup(null, respostaGroup.getTexto(), new Date(System.currentTimeMillis()), m1,
				TipoPostagem.RESPOSTA);
		return resposta1;
	}

	public RespostaGroup adicionarResposta(RespostaGroup respostaGroup, Integer idMember, Integer idPost) {
		PostagemGrupo postP = postagemRepo.findById(idPost).orElse(null);
		RespostaGroup resposta1 = addResposta(respostaGroup, idMember);
		if(resposta1.getTexto() == null || resposta1.getTexto() == "") {
			System.out.println("POST INCORRETO!");
		}else {
			respostaRepo.save(resposta1);
			postP.getResposta().add(resposta1);
			postagemRepo.flush();
		}
		return resposta1;
	}
}
