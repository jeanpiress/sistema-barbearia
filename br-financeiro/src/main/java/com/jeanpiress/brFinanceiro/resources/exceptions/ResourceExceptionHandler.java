package com.jeanpiress.brFinanceiro.resources.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemUsuario = "Mensagem invalida";
		String mensagemDesenvolvedor = ex.getCause().toString();
		List<DescricaoErros> erros = Arrays.asList(new DescricaoErros(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleErroDeleteRecursoNaoEncontrado(EmptyResultDataAccessException ex,
			WebRequest request) {
		String mensagemUsuario = "recurso não existe";
		String mensagemDesenvolvedor = ex.toString();
		List<DescricaoErros> erros = Arrays.asList(new DescricaoErros(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<Object> handleRecursoNaoEncontrado(NoSuchElementException ex,
			WebRequest request) {
		String mensagemUsuario = "recurso não existe";
		String mensagemDesenvolvedor = ex.toString();
		List<DescricaoErros> erros = Arrays.asList(new DescricaoErros(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleErroIncersaoCampoNull(ConstraintViolationException ex,
			WebRequest request) {
		String mensagemUsuario = "recurso não pode ser nulo";
		String mensagemDesenvolvedor = ex.toString();
		List<DescricaoErros> erros = Arrays.asList(new DescricaoErros(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({PSQLException.class})
	public ResponseEntity<Object> handleErroApagarRecursoEmUso(PSQLException ex,
			WebRequest request) {
		String mensagemUsuario = "recurso não pode ser deletado";
		String mensagemDesenvolvedor = ex.toString();
		List<DescricaoErros> erros = Arrays.asList(new DescricaoErros(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
}
