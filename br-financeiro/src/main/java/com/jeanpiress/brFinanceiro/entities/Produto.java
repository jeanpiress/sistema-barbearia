package com.jeanpiress.brFinanceiro.entities;

import java.io.Serializable;


public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String nome;
	private Double valor;
	private Double custo;
	private Double comissao;
	private boolean temEstoque;
	private Integer estoque;

	

	

	public Produto() {

	}

	public Produto(String nome, Double valor, Double custo, Double comissao, boolean temEstoque,
			Integer estoque) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.custo = custo;
		this.comissao = comissao;
		this.temEstoque = temEstoque;
		this.estoque = estoque;
		
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public boolean isTemEstoque() {
		return temEstoque;
	}

	public void setTemEstoque(boolean temEstoque) {
		this.temEstoque = temEstoque;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	

}
