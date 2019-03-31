package com.giovanijfc.sistemadepostagens.dto;

import javax.validation.constraints.Email;

import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;

public class UsuarioDTO {

	private Integer id;

	private String nome;

	private String email;

	private String senha;

	private String descricao;

	private String urlFotoPerfil;

	private Cargo cargo;

	public UsuarioDTO(Integer id, String nome, @Email String email, String senha, String descricao,
			String urlFotoPerfil, Cargo cargo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.descricao = descricao;
		this.urlFotoPerfil = urlFotoPerfil;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrlFotoPerfil() {
		return urlFotoPerfil;
	}

	public void setUrlFotoPerfil(String urlFotoPerfil) {
		this.urlFotoPerfil = urlFotoPerfil;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
}
