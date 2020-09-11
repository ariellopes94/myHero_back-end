package com.myhero.resources.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myhero.services.exceptions.AuthotizationException;
import com.myhero.services.exceptions.CodigoDeSeguranca;
import com.myhero.services.exceptions.ObjectNotFoundException;
import com.myhero.services.exceptions.ValidationError;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> handlerObjectNotFoundException(ObjectNotFoundException e,
			                                                            HttpServletRequest request){
		StandardError erro = new StandardError();
		
		erro.setTimestamp(System.currentTimeMillis());
		erro.setStatus(404);
		erro.setError("Não encontrado");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		erro.setMensagemDesenvolvedor("http://erros.sistemapedidos.com/404");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(CodigoDeSeguranca.class)
	public ResponseEntity<StandardError> handlerCodigoDeSeguranca(CodigoDeSeguranca e,
			                                                            HttpServletRequest request){
		StandardError erro = new StandardError();
		
		erro.setTimestamp(System.currentTimeMillis());
		erro.setStatus(404);
		erro.setError("Não encontrado");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		erro.setMensagemDesenvolvedor("http://erros.sistemapedidos.com/404");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e,
			                                                            HttpServletRequest request){
	
		ValidationError erro = new ValidationError();
		
	   for(FieldError x : e.getBindingResult().getFieldErrors()) {
		   erro.addError(x.getField(), x.getDefaultMessage());
	   }
			
			
	    erro.setTimestamp(System.currentTimeMillis());
		erro.setStatus(422);
		erro.setError("Erro de Validação");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		erro.setMensagemDesenvolvedor("http://erros.sistemapedidos.com/422");
		
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
	}
	@ExceptionHandler(AuthotizationException.class)
	public ResponseEntity<StandardError> authorization(AuthotizationException e,
			                                                            HttpServletRequest request){
	
		StandardError erro = new StandardError();
		erro.setTimestamp(System.currentTimeMillis());
		erro.setStatus(403);
		erro.setError("Acesso negado");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		erro.setMensagemDesenvolvedor("http://erros.sistemapedidos.com/403");
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
}
