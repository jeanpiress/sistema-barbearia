package com.jeanpiress.brFinanceiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class Profissional implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String celular;
	private Instant nascimento;
	private Double salarioFixo;


	public Profissional() {

	}

	public Profissional(Long id, String nome, String celular, Instant nascimento, Double salarioFixo) {
		super();
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.nascimento = nascimento;
		this.salarioFixo = salarioFixo;
		

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

	public Double getSalarioFixo() {
		return salarioFixo;
	}

	public void setSalarioFixo(Double salarioFixo) {
		this.salarioFixo = salarioFixo;
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
		Profissional other = (Profissional) obj;
		return Objects.equals(id, other.id);
	}

	

}
