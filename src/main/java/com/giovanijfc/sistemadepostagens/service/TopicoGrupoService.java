package com.giovanijfc.sistemadepostagens.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.TopicoGrupo;
import com.giovanijfc.sistemadepostagens.repository.GrupoRepository;

@Service
public class TopicoGrupoService {

	@Autowired
	private GrupoRepository grupoRepo;

	public List<TopicoGrupo> buscarTodosGrupo(Integer idGrupo) {
		Grupo grupo = grupoRepo.findById(idGrupo).orElse(null);
		List<TopicoGrupo> listaTopicos = new ArrayList<TopicoGrupo>();
		grupo.getTopicos().stream().forEach(x-> listaTopicos.add(x));
		return listaTopicos;
	}
}
