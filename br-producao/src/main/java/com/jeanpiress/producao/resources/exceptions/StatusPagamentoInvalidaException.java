package com.jeanpiress.producao.resources.exceptions;

public class StatusPagamentoInvalidaException extends IllegalArgumentException{
	private static final long serialVersionUID = 1L;

	public StatusPagamentoInvalidaException() {
		super("Status de pagamento invalida");
	}
}
