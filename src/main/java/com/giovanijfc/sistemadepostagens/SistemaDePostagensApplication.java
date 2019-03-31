package com.giovanijfc.sistemadepostagens;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.Topico;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;
import com.giovanijfc.sistemadepostagens.domain.enums.TipoPostagem;
import com.giovanijfc.sistemadepostagens.repository.GrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.MembroRepository;
import com.giovanijfc.sistemadepostagens.repository.PostagemRepository;
import com.giovanijfc.sistemadepostagens.repository.TopicoRepository;
import com.giovanijfc.sistemadepostagens.repository.UsuarioRepository;

@SpringBootApplication
public class SistemaDePostagensApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private GrupoRepository grupoRepo;
	@Autowired
	private MembroRepository membroRepo;
	@Autowired
	private TopicoRepository topicoRepo;
	@Autowired
	private PostagemRepository postagemRepo;

	public static void main(String[] args) {
		SpringApplication.run(SistemaDePostagensApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(null, "Giovani Fonseca", "", "giovanijfc@gmail.com", "123456", Cargo.USUÁRIO,
				new Date(System.currentTimeMillis()));
		Usuario u2 = new Usuario(null, "Emerson Fonseca", "", "emerson@gmail.com", "123456", Cargo.USUÁRIO,
				new Date(System.currentTimeMillis()));
		Usuario u3 = new Usuario(null, "Gisele Fonseca", "", "giselle@gmail.com", "123456", Cargo.ADMINISTRADOR,
				new Date(System.currentTimeMillis()));
		Usuario u4 = new Usuario(null, "Edson Fonseca", "", "edson@gmail.com", "123456", Cargo.MODERADOR,
				new Date(System.currentTimeMillis()));

		usuarioRepo.saveAll(Arrays.asList(u1, u2, u3, u4));

		Grupo g1 = new Grupo(null, "Um grupo da nossa pequena família", "", "Família Fonseca",
				new Date(System.currentTimeMillis()));
		Grupo g2 = new Grupo(null, "Um grupo de programadores", "", "Programadores",
				new Date(System.currentTimeMillis()));

		Membro m1 = new Membro(null, u1.getNome(), u1.getEmail(), new Date(System.currentTimeMillis()),
				Cargo.ADMINISTRADOR);
		Membro m2 = new Membro(null, u2.getNome(), u2.getEmail(), new Date(System.currentTimeMillis()), Cargo.USUÁRIO);
		Membro m3 = new Membro(null, u3.getNome(), u3.getEmail(), new Date(System.currentTimeMillis()), Cargo.USUÁRIO);
		Membro m4 = new Membro(null, u4.getNome(), u4.getEmail(), new Date(System.currentTimeMillis()), Cargo.USUÁRIO);

		m1.getGrupo().addAll(Arrays.asList(g1, g2));
		m2.getGrupo().addAll(Arrays.asList(g1, g2));
		m3.getGrupo().addAll(Arrays.asList(g1));
		m4.getGrupo().addAll(Arrays.asList(g1));

		membroRepo.saveAll(Arrays.asList(m1, m2, m3, m4));

		g1.getMembro().addAll(Arrays.asList(m1, m2, m3, m4));
		g2.getMembro().addAll(Arrays.asList(m1, m2));
	
		Topico t1 = new Topico(null);
		Topico t2 = new Topico(null);
		
		Postagem p1 = new Postagem(null, "Boa Noite!", new Date(System.currentTimeMillis()), u1, TipoPostagem.POSTAGEM);
		Postagem r1 = new Postagem(null, "Boa Noite!", new Date(System.currentTimeMillis()), u2, TipoPostagem.RESPOSTA);

		Postagem p2 = new Postagem(null, "Olha esse if(1>2){System.out.println('AAAAAAA')}",
				new Date(System.currentTimeMillis()), u1, TipoPostagem.POSTAGEM);
		Postagem r2 = new Postagem(null, "Cara você ta viajando...", new Date(System.currentTimeMillis()), u2,
				TipoPostagem.RESPOSTA);

		p1.getResposta().addAll(Arrays.asList(r1));
		p2.getResposta().addAll(Arrays.asList(r2));

		postagemRepo.saveAll(Arrays.asList(p1, r1, p2, r2));

		t1.setPostPrincipal(p1);
		t2.setPostPrincipal(p2);
		topicoRepo.saveAll(Arrays.asList(t1, t2));

		
		g1.getTopicos().addAll(Arrays.asList(t1));
		g2.getTopicos().addAll(Arrays.asList(t2));
		
		grupoRepo.saveAll(Arrays.asList(g1, g2));
		postagemRepo.flush();

	}
}
