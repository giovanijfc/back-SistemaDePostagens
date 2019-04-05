package com.giovanijfc.sistemadepostagens.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Amizade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnoreProperties({"amizade"})
	@OneToOne
	private Usuario usuarioPrincipal;
	
	@JsonIgnoreProperties({"amizade"})
	@OneToOne
	private Usuario usuarioSecundario;
	
	private Date data;
	
	public Amizade() {
		
	}
	
	public Amizade(Integer id, Usuario usuarioPrincipal, Usuario usuarioSecundario, Date data) {
		super();
		this.id = id;
		this.usuarioPrincipal = usuarioPrincipal;
		this.usuarioSecundario = usuarioSecundario;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuarioPrincipal() {
		return usuarioPrincipal;
	}

	public void setUsuarioPrincipal(Usuario usuarioPrincipal) {
		this.usuarioPrincipal = usuarioPrincipal;
	}

	public Usuario getUsuarioSecundario() {
		return usuarioSecundario;
	}

	public void setUsuarioSecundario(Usuario usuarioSecundario) {
		this.usuarioSecundario = usuarioSecundario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
}
