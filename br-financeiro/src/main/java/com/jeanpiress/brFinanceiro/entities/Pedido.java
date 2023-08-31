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
	
	private Double comissao;
	
	private Integer pagamentoStatus;
	
	private Integer formaPagamento;

	public Pedido() {

	}

	public Pedido(Instant horario, String descricao, Profissional profissional,
			List<Produto> produtos, Double valorTotal, Double comissao, Integer pagamentoStatus, Integer formaPagamento) {
		super();
		this.horario = horario;
		this.descricao = descricao;
		this.profissional = profissional;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.comissao = comissao;
		this.pagamentoStatus = pagamentoStatus;
		this.formaPagamento = formaPagamento;
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

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public Integer getPagamentoStatus() {
		return pagamentoStatus;
	}

	public void setPagamentoStatus(Integer pagamentoStatus) {
		this.pagamentoStatus = pagamentoStatus;
	}

	public Integer getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Integer formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
		
	
		

}
