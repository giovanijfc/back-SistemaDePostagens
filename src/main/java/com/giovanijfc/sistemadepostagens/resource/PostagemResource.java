package com.giovanijfc.sistemadepostagens.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.Resposta;
import com.giovanijfc.sistemadepostagens.service.PostagemService;

@RestController
@RequestMapping(value = "/post")
public class PostagemResource {

	@Autowired
	private PostagemService postagemSer;

	@RequestMapping(value = "/buscartodos", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarTodo() {
		List<Postagem> list = postagemSer.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ResponseEntity<Postagem> buscarPorEmail(@RequestParam(value = "id") Integer id) {
		Postagem postagem = postagemSer.buscarPorIdPost(id);
		return ResponseEntity.ok().body(postagem);
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ResponseEntity<Postagem> adicionar(@RequestBody Postagem postagem, @RequestParam(value = "id") Integer idUser) {
		postagemSer.adicionar(postagem, idUser);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.DELETE)
	public ResponseEntity<Postagem> deletar(@RequestParam(value = "id") Integer id) {
		postagemSer.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/editar", method=RequestMethod.PUT)
	public ResponseEntity<Postagem> atualizar(@RequestBody String texto, @RequestParam(value="id")Integer id){
		postagemSer.atualizar(texto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/responder/{idPostP}", method=RequestMethod.POST)
	public ResponseEntity<Postagem> adicionarResposta(@RequestBody Resposta resposta, @RequestParam(value="idUser")Integer idUser, @PathVariable Integer idPostP){
		postagemSer.adicionarResposta(resposta, idUser, idPostP);
		return ResponseEntity.noContent().build();
	}
}
