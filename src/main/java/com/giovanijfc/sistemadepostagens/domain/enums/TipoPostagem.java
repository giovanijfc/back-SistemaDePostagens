package com.giovanijfc.sistemadepostagens.domain.enums;

public enum TipoPostagem {
	POSTAGEM("Postagem"),
	RESPOSTA("Resposta");
	
	private String tipo;

	private TipoPostagem(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
