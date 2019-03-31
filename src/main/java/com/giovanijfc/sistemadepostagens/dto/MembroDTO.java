package com.giovanijfc.sistemadepostagens.dto;

import java.io.Serializable;

import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;

public class MembroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String email;

	private String nomeDoGrupo;
	
	private Cargo cargo;

	public MembroDTO(Integer id, String nome, String email, String nomeDoGrupo, Cargo cargo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.nomeDoGrupo = nomeDoGrupo;
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeDoGrupo() {
		return nomeDoGrupo;
	}

	public void setNomeDoGrupo(String nomeDoGrupo) {
		this.nomeDoGrupo = nomeDoGrupo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}