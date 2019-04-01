package com.giovanijfc.sistemadepostagens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String nome;
	private String descricao;
	private String urlFotoGrupo;

	private String criado;

	@OneToMany
	@JoinColumn(name="topico_id")
	private List<TopicoGrupo> topicos = new ArrayList<TopicoGrupo>();

	@Column(unique = true)
	@ManyToMany
	private List<Membro> membros = new ArrayList<Membro>();

	public Grupo() {
	}

	public Grupo(Integer id, String descriçao, String urlFotoGrupo, String nome, String entrada) {
		super();
		this.id = id;
		this.descricao = descriçao;
		this.urlFotoGrupo = urlFotoGrupo;
		this.nome = nome;
		this.criado = entrada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descriçao) {
		this.descricao = descriçao;
	}

	public String getUrlFotoGrupo() {
		return urlFotoGrupo;
	}

	public void setUrlFotoGrupo(String urlFotoGrupo) {
		this.urlFotoGrupo = urlFotoGrupo;
	}

	public List<TopicoGrupo> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<TopicoGrupo> topicos) {
		this.topicos = topicos;
	}

	public String getEntrada() {
		return criado;
	}

	public void setEntrada(String entrada) {
		this.criado = entrada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Membro> getMembro() {
		return membros;
	}

	public void setMembro(List<Membro> membro) {
		this.membros = membro;
	}
	
}
