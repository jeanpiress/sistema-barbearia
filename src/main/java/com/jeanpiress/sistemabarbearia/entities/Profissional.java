package com.jeanpiress.sistemabarbearia.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_profissional")
public class Profissional implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Instant nascimento;
	private String telefone;
	private String endereco;
	private String usuario;
	private String senha;
	private Double salarioFixo;
	private Double comissao;
	
	public Profissional() {
		
	}
		
	public Profissional(Long id, String nome, Instant nascimento, String telefone, String endereco, String usuario, String senha,
			Double salarioFixo, Double comissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.usuario = usuario;
		this.senha = senha;
		this.salarioFixo = salarioFixo;
		this.comissao = comissao;
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

	public Instant getNascimento() {
		return nascimento;
	}

	public void setNascimento(Instant nascimento) {
		this.nascimento = nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Double getSalario() {
		return salarioFixo;
	}

	public void setSalario(Double salarioFixo) {
		this.salarioFixo = salarioFixo;
	}
	
	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
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
