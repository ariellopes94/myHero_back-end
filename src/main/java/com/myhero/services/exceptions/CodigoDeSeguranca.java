package com.myhero.services.exceptions;

public class CodigoDeSeguranca extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CodigoDeSeguranca(String mensagem) {
		super(mensagem);
	}
	
	public CodigoDeSeguranca(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
