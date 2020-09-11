package com.myhero.domain.dto.input;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class CodigoDeSegurancaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String codigoDeSeguranca;
	
	public CodigoDeSegurancaDTO() {
	}

	public String getCodigoDeSeguranca() {
		return codigoDeSeguranca;
	}

	public void setCodigoDeSeguranca(String codigoDeSeguranca) {
		this.codigoDeSeguranca = codigoDeSeguranca;
	}
}