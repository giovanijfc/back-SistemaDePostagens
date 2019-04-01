package com.giovanijfc.sistemadepostagens.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.Topico;
import com.giovanijfc.sistemadepostagens.service.TopicoService;

@RestController
@RequestMapping(value = "/topico")
public class TopicoResource {

	@Autowired
	private TopicoService topicoSer;
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ResponseEntity<Topico> buscar(@RequestParam(value = "idTopico") Integer idTopico) {
		return ResponseEntity.ok().body(topicoSer.buscar(idTopico));
	}
	
	@RequestMapping(value = "/buscarTodosPostUser", method = RequestMethod.GET)
	public ResponseEntity<List<Topico>> buscarTodos(@RequestParam(value = "idUser") Integer idUser) {
		return ResponseEntity.ok().body(topicoSer.buscarTodosPostSeu(idUser));
	}

	@RequestMapping(value="/buscarTodosPostAmigos", method=RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarTodosAmizade(@RequestParam(value="idUser") Integer idUser){
			return ResponseEntity.ok().body(topicoSer.buscarTodosAmizade(idUser));
	}
	
}
