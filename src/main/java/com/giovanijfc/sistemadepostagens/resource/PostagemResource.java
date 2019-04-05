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
@RequestMapping(value = "/postagem")
public class PostagemResource {

	@Autowired
	private PostagemService postagemSer;

	@RequestMapping(value = "/buscarTodos", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarTodo() {
		List<Postagem> list = postagemSer.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ResponseEntity<Postagem> buscarPorEmail(@RequestParam(value = "id") Integer id) {
		Postagem postagem = postagemSer.buscarPorIdPost(id);
		return ResponseEntity.ok().body(postagem);
	}

	@RequestMapping(value = "/adicionarPostagem", method = RequestMethod.POST)
	public ResponseEntity<Postagem> adicionar(@RequestBody String postagem, @RequestParam(value = "id") Integer idUser) {
		postagemSer.adicionar(postagem, idUser);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/deletarPostagem", method = RequestMethod.DELETE)
	public ResponseEntity<Postagem> deletarPostagem(@RequestParam(value = "id") Integer id) {
		postagemSer.deletePostagem(id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "/deletarResposta", method = RequestMethod.DELETE)
	public ResponseEntity<Postagem> deletarResposta(@RequestParam(value = "id") Integer id) {
		postagemSer.deleteResposta(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/editarPostagem", method=RequestMethod.PUT)
	public ResponseEntity<Postagem> atualizar(@RequestBody String texto, @RequestParam(value="id")Integer id){
		postagemSer.atualizar(texto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/responderPost/{idPostP}", method=RequestMethod.POST)
	public ResponseEntity<Postagem> adicionarResposta(@RequestBody Resposta resposta, @RequestParam(value="idUser")Integer idUser, @PathVariable Integer idPostP){
		postagemSer.adicionarResposta(resposta, idUser, idPostP);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/editarResposta", method=RequestMethod.PUT)
	public ResponseEntity<Postagem> atualizarResposta(@RequestBody String texto, @RequestParam(value="id")Integer id){
		postagemSer.atualizarResposta(texto, id);
		return ResponseEntity.noContent().build();
	}
}
