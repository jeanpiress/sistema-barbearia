package com.jeanpiress.brFinanceiro.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;

@Entity
@Table(name = "tb_gastosFixos")
public class GastoFixo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Double valor;
	
	private Credor credor;
	
	private String motivo;
	
	private Instant dataVencimento;
	
	private boolean ativo;
	
	private Integer pagamentoStatus;

	public GastoFixo() {
		
	}
	
	public GastoFixo(Long id, String nome, Double valor, Credor credor, String motivo, Instant dataVencimento, boolean ativo, PagamentoStatus pagamentoStatus) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.credor = credor;
		this.motivo = motivo;
		this.dataVencimento = dataVencimento;
		this.ativo = ativo;
		setPagamentoStatus(pagamentoStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Credor getCredor() {
		return credor;
	}

	public void setCredor(Credor credor) {
		this.credor = credor;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Instant getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Instant dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
		GastoFixo other = (GastoFixo) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
