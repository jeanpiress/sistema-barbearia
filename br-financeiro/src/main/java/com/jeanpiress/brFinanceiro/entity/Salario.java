package com.jeanpiress.brFinanceiro.entity;

import java.util.List;

public class Salario {
	
	private String nomeProfissional;
	
	private Double salarioFixo;
	private Double comissao;
	
	private List<Pedido> pedidos;
	
	public Salario() {
		
	}
	
	public Salario(String nomeProfissional, Double salarioFixo, Double comissao) {
		super();
		this.nomeProfissional = nomeProfissional;
		this.salarioFixo = salarioFixo;
		this.comissao = comissao;
	}

	
	
	public String getNomeProfissional() {
		return nomeProfissional;
	}

	public void setNomeProfissional(String nomeProfissional) {
		this.nomeProfissional = nomeProfissional;
	}

	public Double getSalarioFixo() {
		return salarioFixo;
	}

	public void setSalarioFixo(Double salarioFixo) {
		this.salarioFixo = salarioFixo;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}
	
		
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
}
