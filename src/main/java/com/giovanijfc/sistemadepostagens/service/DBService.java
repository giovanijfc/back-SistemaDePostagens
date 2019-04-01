package com.giovanijfc.sistemadepostagens.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.PostagemGrupo;
import com.giovanijfc.sistemadepostagens.domain.Resposta;
import com.giovanijfc.sistemadepostagens.domain.RespostaGroup;
import com.giovanijfc.sistemadepostagens.domain.Topico;
import com.giovanijfc.sistemadepostagens.domain.TopicoGrupo;
import com.giovanijfc.sistemadepostagens.domain.Usuario;
import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;
import com.giovanijfc.sistemadepostagens.domain.enums.TipoPostagem;
import com.giovanijfc.sistemadepostagens.repository.GrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.MembroRepository;
import com.giovanijfc.sistemadepostagens.repository.PostagemGrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.PostagemRepository;
import com.giovanijfc.sistemadepostagens.repository.RespostaGrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.RespostaRepository;
import com.giovanijfc.sistemadepostagens.repository.TopicoGrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.TopicoRepository;
import com.giovanijfc.sistemadepostagens.repository.UsuarioRepository;

@Service
public class DBService implements CommandLineRunner {

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
	@Autowired
	private PostagemGrupoRepository postGrupoRepo;
	@Autowired
	private TopicoGrupoRepository topGrupoRepo;
	@Autowired
	private RespostaGrupoRepository respostaGrupoRepo;
	@Autowired
	private RespostaRepository respostaRepo;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss");

		Usuario u1 = new Usuario(null, "Giovani Fonseca", "", "giovanijfc@gmail.com", "123456", Cargo.USUÁRIO,
				sdf.format(new Date(System.currentTimeMillis())));
		Usuario u2 = new Usuario(null, "Emerson Fonseca", "", "emerson@gmail.com", "123456", Cargo.USUÁRIO,
				sdf.format(new Date(System.currentTimeMillis())));
		Usuario u3 = new Usuario(null, "Gisele Fonseca", "", "giselle@gmail.com", "123456", Cargo.ADMINISTRADOR,
				sdf.format(new Date(System.currentTimeMillis())));
		Usuario u4 = new Usuario(null, "Edson Fonseca", "", "edson@gmail.com", "123456", Cargo.MODERADOR,
				sdf.format(new Date(System.currentTimeMillis())));

		usuarioRepo.saveAll(Arrays.asList(u1, u2, u3, u4));

		Grupo g1 = new Grupo(null, "Um grupo da nossa pequena família", "", "Família Fonseca",
				sdf.format(new Date(System.currentTimeMillis())));
		Grupo g2 = new Grupo(null, "Um grupo de programadores", "", "Programadores",
				sdf.format(new Date(System.currentTimeMillis())));

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

		TopicoGrupo t3 = new TopicoGrupo(null);

		PostagemGrupo p3 = new PostagemGrupo(null, "Boa Noite!", sdf.format(new Date(System.currentTimeMillis())),
				sdff.format(new Date(System.currentTimeMillis())), m1, TipoPostagem.POSTAGEM);
		RespostaGroup r3 = new RespostaGroup(null, "Boa Noite!", sdf.format(new Date(System.currentTimeMillis())),
				sdff.format(new Date(System.currentTimeMillis())), m2, TipoPostagem.RESPOSTA);
		RespostaGroup r4 = new RespostaGroup(null, "Boa Noite!", sdf.format(new Date(System.currentTimeMillis())),
				sdff.format(new Date(System.currentTimeMillis())), m2, TipoPostagem.RESPOSTA);

		Postagem p1 = new Postagem(null, "Boa Noite!", sdf.format(new Date(System.currentTimeMillis())),
				sdff.format(new Date(System.currentTimeMillis())), u1, TipoPostagem.POSTAGEM);
		Resposta r1 = new Resposta(null, "Boa Noite!", sdf.format(new Date(System.currentTimeMillis())),
				sdff.format(new Date(System.currentTimeMillis())), u2, TipoPostagem.RESPOSTA);

		Postagem p2 = new Postagem(null, "Olha esse if(1>2){System.out.println('AAAAAAA')}",
				sdf.format(new Date(System.currentTimeMillis())), sdff.format(new Date(System.currentTimeMillis())), u1,
				TipoPostagem.POSTAGEM);
		Resposta r2 = new Resposta(null, "Larga de ser doente cara", sdf.format(new Date(System.currentTimeMillis())),
				sdff.format(new Date(System.currentTimeMillis())), u4, TipoPostagem.POSTAGEM);

		respostaGrupoRepo.saveAll(Arrays.asList(r3, r4));
		respostaRepo.saveAll(Arrays.asList(r1, r2));

		p1.getResposta().add(r1);
		p2.getResposta().add(r2);
		p3.getResposta().addAll(Arrays.asList(r3, r4));

		postagemRepo.saveAll(Arrays.asList(p1, p2));
		postGrupoRepo.saveAll(Arrays.asList(p3));

		t1.setPostPrincipal(p1);
		t2.setPostPrincipal(p2);
		t3.setPostPrincipal(p3);
		topicoRepo.saveAll(Arrays.asList(t1, t2));
		topGrupoRepo.save(t3);

		g1.getTopicos().add(t3);

		grupoRepo.saveAll(Arrays.asList(g1, g2));
		postagemRepo.flush();

	}

}
