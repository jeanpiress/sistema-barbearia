package com.jeanpiress.brFinanceiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	private String celular;
	private Instant nascimento;
	private Instant ultimaVisita;
	private Integer pontos;
	private Integer diasParaVoltar;
	private Instant previsaoRetorno;
	private String observacao;
	
	
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {
		
	}
	
	public Cliente(Long id, String nome, String celular, Instant nascimento, Instant ultimaVisita, Integer pontos,
			Integer diasParaVoltar, Instant previsaoRetorno, String observacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.nascimento = nascimento;
		this.ultimaVisita = ultimaVisita;
		this.pontos = pontos;
		this.diasParaVoltar = diasParaVoltar;
		this.previsaoRetorno = previsaoRetorno;
		this.observacao = observacao;
		
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Instant getNascimento() {
		return nascimento;
	}

	public void setNascimento(Instant nascimento) {
		this.nascimento = nascimento;
	}

	public Instant getUltimaVisita() {
		return ultimaVisita;
	}

	public void setUltimaVisita(Instant ultimaVisita) {
		this.ultimaVisita = ultimaVisita;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getDiasParaVoltar() {
		return diasParaVoltar;
	}

	public void setDiasParaVoltar(Integer diasParaVoltar) {
		this.diasParaVoltar = diasParaVoltar;
	}

	public Instant getPrevisaoRetorno() {
		return previsaoRetorno;
	}

	public void setPrevisaoRetorno(Instant previsaoRetorno) {
		this.previsaoRetorno = previsaoRetorno;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	

	
	
	

}
