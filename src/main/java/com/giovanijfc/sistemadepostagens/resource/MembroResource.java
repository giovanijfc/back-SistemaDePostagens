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

import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.dto.MembroDTO;
import com.giovanijfc.sistemadepostagens.service.MembroService;

@RestController
@RequestMapping(value="/membro")
public class MembroResource {
	
	@Autowired
	private MembroService membroSer;
	
	@RequestMapping(value="/buscar", method=RequestMethod.GET)
	public ResponseEntity<Membro> buscarPorEmail(@PathParam(value="email") String email){
		Membro obj = membroSer.buscarPorEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/adicionarMembro", method=RequestMethod.POST)
	public ResponseEntity<Membro> adicionar(@RequestBody Membro membroDto){
		membroSer.adicionar(membroDto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/deletarMembro/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Membro> deletar(@PathVariable Integer id){
		membroSer.delete(id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/atualizarMembro", method=RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizar(@RequestBody MembroDTO membroDto, @RequestParam(value="email")String email){
		membroSer.atualizar(membroDto, email);
		return ResponseEntity.noContent().build();
	}
}
