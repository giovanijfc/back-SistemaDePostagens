package com.giovanijfc.sistemadepostagens.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.TopicoGrupo;
import com.giovanijfc.sistemadepostagens.repository.GrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.TopicoGrupoRepository;

@Service
public class TopicoGrupoService {

	@Autowired
	private GrupoRepository grupoRepo;
	@Autowired
	private TopicoGrupoRepository topicoRepo;

	public List<TopicoGrupo> buscarTodosGrupo(Integer idGrupo) {
		Grupo grupo = grupoRepo.findById(idGrupo).orElse(null);
		List<TopicoGrupo> listaTopicos = new ArrayList<TopicoGrupo>();
		grupo.getTopicos().stream().forEach(x-> listaTopicos.add(x));
		return listaTopicos;
	}
	
	public TopicoGrupo buscar(Integer idTopico) {
		return topicoRepo.findById(idTopico).orElse(null);
	}
}
