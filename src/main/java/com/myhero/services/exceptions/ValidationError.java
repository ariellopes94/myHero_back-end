package com.myhero.services.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.myhero.resources.handler.StandardError;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
	}
	
	

	public ValidationError(Integer status, String error, String message, String path, String mensagemDesenvolvedor) {
		super(status, error, message, path, mensagemDesenvolvedor);
	}



	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}
	
}

