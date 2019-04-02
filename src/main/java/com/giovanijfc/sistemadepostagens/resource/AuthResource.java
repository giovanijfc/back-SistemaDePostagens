package com.giovanijfc.sistemadepostagens.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.dto.ChangedNewPassDTO;
import com.giovanijfc.sistemadepostagens.repository.UsuarioRepository;
import com.giovanijfc.sistemadepostagens.security.JWTUtil;
import com.giovanijfc.sistemadepostagens.security.UserSS;
import com.giovanijfc.sistemadepostagens.service.UserService;
import com.giovanijfc.sistemadepostagens.service.UsuarioService;
import com.giovanijfc.sistemadepostagens.service.exceptions.DataIntegrityException;
import com.giovanijfc.sistemadepostagens.service.exceptions.ForbiddenException;

@RestController	
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
		
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.PUT)
	public String  forgot(@RequestBody ChangedNewPassDTO objDto) {
		Usuario user = repo.findByEmail(objDto.getEmail());
		if (user == null) {
			throw new DataIntegrityException("Email incorreto!");
		}
		if ((pe.matches(objDto.getPalavraChave(), user.getPalavraChave()))
				&& objDto.getEmail().equals(user.getEmail())) {
			return usuarioService.changePass(objDto.getNewSenha(), user);
		}
		throw new ForbiddenException("Falha na tentativa de autenticação!");
	}
}
