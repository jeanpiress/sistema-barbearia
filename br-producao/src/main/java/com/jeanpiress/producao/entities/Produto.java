package com.jeanpiress.producao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private Double valor;
	private Double custo;
	@NotNull
	private Double comissao;
	private boolean temEstoque;
	private Integer estoque;

	@ManyToMany(mappedBy = "produtos")
	@JsonIgnore
	private List<Pedido> pedidos = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@JsonIgnore
	private CategoriaProduto categoria;

	public Produto() {

	}

	public Produto(Long id, String nome, Double valor, Double custo, Double comissao, boolean temEstoque,
			Integer estoque, CategoriaProduto categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.custo = custo;
		this.comissao = comissao;
		this.temEstoque = temEstoque;
		this.estoque = estoque;
		this.categoria = categoria;
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

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public boolean isTemEstoque() {
		return temEstoque;
	}

	public void setTemEstoque(boolean temEstoque) {
		this.temEstoque = temEstoque;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}
