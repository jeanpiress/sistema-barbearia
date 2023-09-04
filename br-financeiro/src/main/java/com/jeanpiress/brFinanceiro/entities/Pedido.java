package com.jeanpiress.brFinanceiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.jeanpiress.brFinanceiro.enums.FormaPagamento;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;


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
	
	private Instant momentoPagamento;

	public Pedido() {

	}

	public Pedido(Instant horario, String descricao, Profissional profissional, List<Produto> produtos,
			 Double valorTotal, Double comissao, PagamentoStatus pagamentoStatus, FormaPagamento formaPagamento, Instant momentoPagamento) {
		super();
		this.horario = horario;
		this.descricao = descricao;
		this.profissional = profissional;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.comissao = comissao;
		setPagamentoStatus(pagamentoStatus);
		setFormaPagamento(formaPagamento);
		this.momentoPagamento = momentoPagamento;
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

	public PagamentoStatus getPagamentoStatus() {
		return PagamentoStatus.valueOf(pagamentoStatus) ;
	}

	public void setPagamentoStatus(PagamentoStatus pagamentoStatus) {
		if(pagamentoStatus != null) {
		this.pagamentoStatus = pagamentoStatus.getCode();
		}
	}
	
	public FormaPagamento getFormaPagamento() {
		return FormaPagamento.valueOf(formaPagamento) ;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		if(formaPagamento != null) {
		this.formaPagamento = formaPagamento.getCode();
		}
	}

	public Instant getMomentoPagamento() {
		return momentoPagamento;
	}

	public void setMomentoPagamento(Instant momentoPagamento) {
		this.momentoPagamento = momentoPagamento;
	}
	
	
		
	
		

}
