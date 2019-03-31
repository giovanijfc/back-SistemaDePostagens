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

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.PostagemGrupo;
import com.giovanijfc.sistemadepostagens.domain.RespostaGroup;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.service.GrupoService;

@RestController
@RequestMapping(value = "/group")
public class GrupoResource {

	@Autowired
	private GrupoService grupoSer;

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ResponseEntity<Grupo> buscarPorEmail(@PathParam(value = "name") String name) {
		Grupo obj = grupoSer.buscarPorNome(name);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ResponseEntity<Grupo> adicionar(@RequestBody Grupo grupo) {
		grupoSer.adicionar(grupo);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Grupo> deletar(@PathVariable Integer id) {
		grupoSer.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizar(@RequestBody Grupo grupo,
			@RequestParam(value = "idGroup") Integer idGrupo) {
		grupoSer.atualizar(grupo, idGrupo);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/adicionarpost/{idGrupo}", method = RequestMethod.POST)
	public ResponseEntity<Usuario> adicionarPostagem(@RequestBody PostagemGrupo postagem,
			@RequestParam(value = "id") Integer idUser, @PathVariable Integer idGrupo) {
		grupoSer.adicionarPostagem(postagem, idUser, idGrupo);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/responder/{idPostP}", method=RequestMethod.POST)
	public ResponseEntity<Postagem> adicionarResposta(@RequestBody RespostaGroup respostaGroup, @RequestParam(value="idUser")Integer idUser, @PathVariable Integer idPostP){
		grupoSer.adicionarResposta(respostaGroup, idUser, idPostP);
		return ResponseEntity.noContent().build();
	}
}
