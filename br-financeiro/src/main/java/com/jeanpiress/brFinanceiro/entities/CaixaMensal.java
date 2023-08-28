package com.jeanpiress.brFinanceiro.entities;

import java.util.ArrayList;
import java.util.List;

public class CaixaMensal {

	private Double taxaCartao;
	private Double faturamento;
	
	private List<Boleto> boletos = new ArrayList<>();
	
	private List<GastoExtraordinario> gastosExtraordinarios = new ArrayList<>();
	
	private List<GastoFixo> gastosFixos = new ArrayList<>();

	
	public CaixaMensal() {
		
	}
	
	public CaixaMensal(Double taxaCartao, Double faturamento, List<Boleto> boletos,
			List<GastoExtraordinario> gastosExtraordinarios, List<GastoFixo> gastosFixos) {
		super();
		this.taxaCartao = taxaCartao;
		this.faturamento = faturamento;
		this.boletos = boletos;
		this.gastosExtraordinarios = gastosExtraordinarios;
		this.gastosFixos = gastosFixos;
	}

	public Double getTaxaCartao() {
		return taxaCartao;
	}

	public void setTaxaCartao(Double taxaCartao) {
		this.taxaCartao = taxaCartao;
	}

	public Double getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(Double faturamento) {
		this.faturamento = faturamento;
	}

	public List<Boleto> getBoletos() {
		return boletos;
	}

	public void setBoletos(List<Boleto> boletos) {
		this.boletos = boletos;
	}

	public List<GastoExtraordinario> getGastosExtraordinarios() {
		return gastosExtraordinarios;
	}

	public void setGastosExtraordinarios(List<GastoExtraordinario> gastosExtraordinarios) {
		this.gastosExtraordinarios = gastosExtraordinarios;
	}

	public List<GastoFixo> getGastosFixos() {
		return gastosFixos;
	}

	public void setGastosFixos(List<GastoFixo> gastosFixos) {
		this.gastosFixos = gastosFixos;
	}

	

}
