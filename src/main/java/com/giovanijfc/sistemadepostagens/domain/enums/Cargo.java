package com.giovanijfc.sistemadepostagens.domain.enums;

public enum Cargo {

	ADMINISTRADOR(1, "ROLE_ADMIN"),
	MODERADOR(2, "ROLE_MOD"),
	USUÁRIO(3, "ROLE_USER");
	
	private String cargo;
	private int cod;

	

	private Cargo(int cod, String cargo) {
		this.cargo = cargo;
		this.cod = cod;
	}

	public String getCargos() {
		return cargo;
	}

	public void setCargos(String cargos) {
		this.cargo = cargos;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public static Cargo toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Cargo x : Cargo.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
