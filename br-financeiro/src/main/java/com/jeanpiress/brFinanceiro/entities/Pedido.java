package com.jeanpiress.brFinanceiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Instant horario;
	private String descricao;

	
	private Profissional profissional;

	
	private List<Produto> produtos = new ArrayList<>();

	private Double valorTotal;

	public Pedido() {

	}

	public Pedido(Instant horario, String descricao, Profissional profissional,
			List<Produto> produtos, Double valorTotal) {
		super();
		this.horario = horario;
		this.descricao = descricao;
		this.profissional = profissional;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
	}

	

	public Instant getHorario() {
		return horario;
	}

	public void setHorario(Instant horario) {
		this.horario = horario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
		
		

}