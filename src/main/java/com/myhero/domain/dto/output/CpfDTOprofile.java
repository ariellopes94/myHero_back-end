package com.myhero.domain.dto.output;

import java.io.Serializable;

public class CpfDTOprofile {

	private Long id;
	private String cpf;
	private String email;
	private String nome;
	private String imageAvatarUrl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getImageAvatarUrl() {
		return imageAvatarUrl;
	}
	public void setImageAvatarUrl(String imageAvatarUrl) {
		this.imageAvatarUrl = imageAvatarUrl;
	}
	
	 
}