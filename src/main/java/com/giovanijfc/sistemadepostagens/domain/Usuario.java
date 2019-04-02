package com.giovanijfc.sistemadepostagens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Email
	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String senha;

	private String dataEntrada;

	private String descricao;

	private String urlFotoPerfil;
	
	private String palavraChave;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="CARGOS")
	private Set<Integer> cargos = new HashSet<Integer>();

	@JsonProperty("amizade")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioPrincipal")
	private List<Amizade> amizade = new ArrayList<Amizade>();

	public Usuario(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.descricao = usuario.getDescricao();
		this.dataEntrada = usuario.getDataEntrada();
		this.palavraChave = usuario.getPalavraChave();
	}

	public Usuario() {
		addPerfil(Cargo.USUÁRIO);
	}

	public Usuario(Integer id, String nome, String descricao, String email, String senha, String date, String palavraChave) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.descricao = descricao;
		this.dataEntrada = date;
		this.palavraChave = palavraChave;
		addPerfil(Cargo.USUÁRIO);
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
		Usuario other = (Usuario) obj;
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

	public List<Amizade> getAmizade() {
		return amizade;
	}

	public void setAmizade(List<Amizade> amizade) {
		this.amizade = amizade;
	}

	public String getUrlFotoPerfil() {
		return urlFotoPerfil;
	}

	public void setUrlFotoPerfil(String urlFotoPerfil) {
		this.urlFotoPerfil = urlFotoPerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	public Set<Cargo> getCargos(){
		return cargos.stream().map(x -> Cargo.toEnum(x)).collect(Collectors.toSet());
	}
	public void addPerfil(Cargo cargo) {
		cargos.add(cargo.getCod());
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	
}
