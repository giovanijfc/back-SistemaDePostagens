package com.giovanijfc.sistemadepostagens.dto;

public class ChangedNewPassDTO {

	private String palavraChave;
	private String email;
	private String novaSenha;
		
	public ChangedNewPassDTO() {
		
	}
	
	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String newSenha) {
		this.novaSenha = newSenha;
	}
}
