package com.giovanijfc.sistemadepostagens.resource;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.dto.UsuarioDTO;
import com.giovanijfc.sistemadepostagens.service.UsuarioService;

@RestController
@RequestMapping(value = "/user")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioSer;

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorEmail(@PathParam(value = "email") String email) {
		Usuario obj = usuarioSer.buscarPorEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ResponseEntity<Usuario> adicionar(@RequestBody UsuarioDTO objDto) {
		usuarioSer.adicionar(objDto);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deletar(@PathVariable Integer id) {
		usuarioSer.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizar(@RequestBody UsuarioDTO objDto,
			@RequestParam(value = "email") String email) {
		Usuario user = usuarioSer.buscarPorEmail(email);
		usuarioSer.atualizarDTO(objDto, user);
		return ResponseEntity.noContent().build();
	}
}
