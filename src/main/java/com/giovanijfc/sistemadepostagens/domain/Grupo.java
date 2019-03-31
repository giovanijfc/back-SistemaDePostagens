package com.giovanijfc.sistemadepostagens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String descriçao;
	private String urlFotoGrupo;

	private Date entrada;

	@OneToMany
	private List<Topico> topicos = new ArrayList<Topico>();

	@ManyToMany
	private List<Membro> membro = new ArrayList<Membro>();

	public Grupo() {
	}

	public Grupo(Integer id, String descriçao, String urlFotoGrupo, String nome, Date entrada ) {
		super();
		this.id = id;
		this.descriçao = descriçao;
		this.urlFotoGrupo = urlFotoGrupo;
		this.nome = nome;
		this.entrada = entrada;
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

	public String getDescriçao() {
		return descriçao;
	}

	public void setDescriçao(String descriçao) {
		this.descriçao = descriçao;
	}

	public String getUrlFotoGrupo() {
		return urlFotoGrupo;
	}

	public void setUrlFotoGrupo(String urlFotoGrupo) {
		this.urlFotoGrupo = urlFotoGrupo;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public List<Membro> getUsuarios() {
		return membro;
	}

	public void setUsuarios(List<Membro> usuarios) {
		this.membro = usuarios;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Membro> getMembro() {
		return membro;
	}

	public void setMembro(List<Membro> membro) {
		this.membro = membro;
	}
}
