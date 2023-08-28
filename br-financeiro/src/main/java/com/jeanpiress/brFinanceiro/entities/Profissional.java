package com.jeanpiress.brFinanceiro.entities;

import java.io.Serializable;
import java.time.Instant;

public class Profissional implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String celular;
	private Instant nascimento;
	private Double salarioFixo;


	public Profissional() {

	}

	public Profissional(String nome, String celular, Instant nascimento, Double salarioFixo) {
		super();
		
		this.nome = nome;
		this.celular = celular;
		this.nascimento = nascimento;
		this.salarioFixo = salarioFixo;
		

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


}
