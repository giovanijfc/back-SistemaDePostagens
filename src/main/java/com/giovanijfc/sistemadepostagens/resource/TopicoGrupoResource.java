package com.giovanijfc.sistemadepostagens.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giovanijfc.sistemadepostagens.domain.TopicoGrupo;
import com.giovanijfc.sistemadepostagens.service.TopicoGrupoService;

@RestController
@RequestMapping(value = "/topicoGrupo")
public class TopicoGrupoResource {

	@Autowired
	private TopicoGrupoService topicoSer;

	@RequestMapping(value = "/buscarTodosUser", method = RequestMethod.GET)
	public ResponseEntity<List<TopicoGrupo>> buscarTodos(@RequestParam(value = "idGrupo") Integer idGrupo) {
		return ResponseEntity.ok().body(topicoSer.buscarTodosGrupo(idGrupo));
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ResponseEntity<TopicoGrupo> buscar(@RequestParam(value = "idTopico") Integer idTopico) {
		return ResponseEntity.ok().body(topicoSer.buscar(idTopico));
	}
}
