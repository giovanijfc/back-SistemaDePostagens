package com.giovanijfc.sistemadepostagens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.giovanijfc.sistemadepostagens.domain.enums.TipoPostagem;

@Entity
public class PostagemGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String texto;
	private Date data;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="resposta_id")
	private List<RespostaGroup> resposta = new ArrayList<RespostaGroup>();

	@OneToOne
	private Membro membro;

	private TipoPostagem tipo;

	public PostagemGrupo() {
	}

	public PostagemGrupo(Integer id, String texto, Date data, Membro m1, TipoPostagem tipo) {
		super();
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.membro = m1;
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
		PostagemGrupo other = (PostagemGrupo) obj;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<RespostaGroup> getResposta() {
		return resposta;
	}

	public void setResposta(List<RespostaGroup> respostaGroup) {
		this.resposta = respostaGroup;
	} 

	public Membro getMembros() {
		return membro;
	}

	public void setMembros(Membro membro) {
		this.membro = membro;
	}

	public TipoPostagem getTipo() {
		return tipo;
	}

	public void setTipo(TipoPostagem tipo) {
		this.tipo = tipo;
	}
}
