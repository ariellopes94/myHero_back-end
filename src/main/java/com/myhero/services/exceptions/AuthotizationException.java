package com.myhero.services.exceptions;

public class AuthotizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AuthotizationException(String mensagem) {
		super(mensagem);
	}
	
	public AuthotizationException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
