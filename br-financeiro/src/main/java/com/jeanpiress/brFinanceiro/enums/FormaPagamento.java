package com.jeanpiress.brFinanceiro.enums;

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
		throw new IllegalArgumentException("Forma de pagamento invalido");
	}
}
