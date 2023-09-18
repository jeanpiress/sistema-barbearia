package com.jeanpiress.producao.entities.enums;

import com.jeanpiress.producao.resources.exceptions.FormaPagamentoInvalidaException;

public enum FormaPagamento {

	DINHEIRO(1), 
	PIX(2), 
	CREDITO(3), 
	DEBITO(4), 
	MISTO(5),
	ESPERANDO(6);

	private int code;

	private FormaPagamento(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static FormaPagamento valueOf(int code) {
		for (FormaPagamento valor : FormaPagamento.values()) {
			if (valor.getCode() == code) {
				return valor;
			}
		}
		throw new FormaPagamentoInvalidaException();
	}
}
