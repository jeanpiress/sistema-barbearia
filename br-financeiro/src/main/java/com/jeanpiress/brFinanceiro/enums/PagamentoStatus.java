package com.jeanpiress.brFinanceiro.enums;

public enum PagamentoStatus {

	APAGAR(1), 
	DIAPAGAMENTO(2), 
	PAGO(3), 
	ATRASADO(4), 
	CANCELADO(5);

	private int code;

	private PagamentoStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PagamentoStatus valueOf(int code) {
		for (PagamentoStatus valor : PagamentoStatus.values()) {
			if (valor.getCode() == code) {
				return valor;
			}
		}
		throw new IllegalArgumentException("Status de pagamento invalido");
	}
}
