package com.giovanijfc.sistemadepostagens.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Amizade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JsonIgnore
	private Usuario usuarioPrincipal;
	
	@OneToOne
	private Usuario usuarioSecundario;
	
	public Amizade() {
		
	}
	
	public Amizade(Integer id, Usuario usuarioPrincipal, Usuario usuarioSecundario) {
		super();
		this.id = id;
		this.usuarioPrincipal = usuarioPrincipal;
		this.usuarioSecundario = usuarioSecundario;
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
}
