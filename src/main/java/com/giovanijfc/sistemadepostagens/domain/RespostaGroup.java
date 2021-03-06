package com.giovanijfc.sistemadepostagens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.giovanijfc.sistemadepostagens.domain.enums.TipoPostagem;

@Entity
public class RespostaGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String texto;
	private String data;
	
	private String hora;

	@OneToOne
	private Membro membros;

	@ManyToMany(fetch=FetchType.LAZY, mappedBy="resposta")
	@JsonIgnore
	private List<PostagemGrupo> postagens = new ArrayList<PostagemGrupo>();
	
	private TipoPostagem tipo;

	public RespostaGroup() {
	}

	public RespostaGroup(Integer id, String texto, String data, String hora, Membro m1, TipoPostagem tipo) {
		super();
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.hora = hora;
		this.membros = m1;
		this.tipo = tipo;
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
		RespostaGroup other = (RespostaGroup) obj;
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Membro getMembros() {
		return membros;
	}

	public void setMembros(Membro membro) {
		this.membros = membro;
	}

	public TipoPostagem getTipo() {
		return tipo;
	}

	public void setTipo(TipoPostagem tipo) {
		this.tipo = tipo;
	}
}
