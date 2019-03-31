package com.giovanijfc.sistemadepostagens.domain.enums;

public enum Cargo {

	ADMINISTRADOR("Administrador"),
	MODERADOR("Moderador"),
	USUÁRIO("Usuário");
	
	private String cargo;

	private Cargo(String cargos) {
		this.cargo = cargos;
	}

	public String getCargos() {
		return cargo;
	}

	public void setCargos(String cargos) {
		this.cargo = cargos;
	}
	
}
