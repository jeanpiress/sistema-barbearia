package com.jeanpiress.producao.resources.exceptions;

public class FormaPagamentoInvalidaException extends IllegalArgumentException{
	private static final long serialVersionUID = 1L;

	public FormaPagamentoInvalidaException() {
		super("Forma de pagamento invalida");
	}
}
