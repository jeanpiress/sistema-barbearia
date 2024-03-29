package com.jeanpiress.brFinanceiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;

@Entity
@Table(name = "tb_gastosExtraordinarios")
public class GastoExtraordinario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double valor;
	
	private Instant dataPagamento;
	
	private Integer pagamentoStatus;

	
	public GastoExtraordinario() {
		
	}
	
	public GastoExtraordinario(Long id, Double valor, Instant dataPagamento, PagamentoStatus pagamentoStatus) {
		super();
		this.id = id;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		setPagamentoStatus(pagamentoStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Instant getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Instant dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public PagamentoStatus getPagamentoStatus() {
		return PagamentoStatus.valueOf(pagamentoStatus) ;
	}

	public void setPagamentoStatus(PagamentoStatus pagamentoStatus) {
		if(pagamentoStatus != null) {
		this.pagamentoStatus = pagamentoStatus.getCode();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GastoExtraordinario other = (GastoExtraordinario) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
