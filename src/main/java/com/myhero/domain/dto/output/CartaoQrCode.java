package com.myhero.domain.dto.output;

import com.myhero.enuns.Sexo;

public class CartaoQrCode {

	private String codigoGeradoPeloSistema;
	private String nome;
	private Integer sexo;
	
	public String getCodigoGeradoPeloSistema() {
		return codigoGeradoPeloSistema;
	}
	public void setCodigoGeradoPeloSistema(String codigoGeradoPeloSistema) {
		this.codigoGeradoPeloSistema = codigoGeradoPeloSistema;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return Sexo.toEnum(sexo).getDescricao();
	}
	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	
	
}
