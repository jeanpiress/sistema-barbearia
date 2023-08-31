package com.jeanpiress.brFinanceiro.entities;

import java.util.ArrayList;
import java.util.List;

public class CaixaMensal {

	
	private Double faturamento;
	
	private List<Salario> salarios = new ArrayList<>();
	
	private List<Boleto> boletos = new ArrayList<>();
	
	private List<GastoExtraordinario> gastosExtraordinarios = new ArrayList<>();
	
	private List<GastoFixo> gastosFixos = new ArrayList<>();

	
	public CaixaMensal() {
		
	}
	
	public CaixaMensal(Double faturamento, List<Salario> salarios, List<Boleto> boletos,
			List<GastoExtraordinario> gastosExtraordinarios, List<GastoFixo> gastosFixos) {
		super();
		this.faturamento = faturamento;
		this.salarios = salarios;
		this.boletos = boletos;
		this.gastosExtraordinarios = gastosExtraordinarios;
		this.gastosFixos = gastosFixos;
	}

	
	public Double getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(Double faturamento) {
		this.faturamento = faturamento;
	}
	
	
	public List<Salario> getSalarios() {
		return salarios;
	}

	public void setSalarios(List<Salario> salarios) {
		this.salarios = salarios;
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
