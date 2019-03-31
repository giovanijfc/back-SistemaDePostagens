package com.giovanijfc.sistemadepostagens.domain;

public class PostAndTopico {

	private Postagem post;
	private Topico topico;

	public PostAndTopico(Postagem post, Topico topico) {
		super();
		this.post = post;
		this.topico = topico;
	}
	public Postagem getPost() {
		return post;
	}
	public void setPost(Postagem post) {
		this.post = post;
	}
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
}
