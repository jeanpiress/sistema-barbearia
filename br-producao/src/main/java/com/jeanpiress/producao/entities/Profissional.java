package com.jeanpiress.producao.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_profissionais")
public class Profissional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;
	@NotNull
	private String celular;
	@NotNull
	private Instant nascimento;
	private Double salarioFixo;

	@OneToMany(mappedBy = "profissional")
	@JsonIgnore
	private List<Pedido> pedidos = new ArrayList<>();

	private Endereco endereco;

	public Profissional() {

	}

	public Profissional(Long id, String nome, String celular, Instant nascimento, Double salarioFixo,
			Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.nascimento = nascimento;
		this.salarioFixo = salarioFixo;
		this.endereco = endereco;

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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
