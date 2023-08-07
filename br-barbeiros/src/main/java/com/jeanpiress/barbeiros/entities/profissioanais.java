package com.jeanpiress.barbeiros.entities;

import java.time.Instant;

public class profissioanais {
	
	private String name;
	private String celular;
	private Instant nascimento;
	private Double salarioFixo;
	
	
	public profissioanais(String name, String celular, Instant nascimento, Double salarioFixo) {
		super();
		this.name = name;
		this.celular = celular;
		this.nascimento = nascimento;
		this.salarioFixo = salarioFixo;
	}
	
	

}
