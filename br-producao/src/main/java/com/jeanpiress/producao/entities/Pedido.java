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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.jeanpiress.producao.entities.enums.FormaPagamento;
import com.jeanpiress.producao.entities.enums.PagamentoStatus;

@Entity
@Table(name = "tb_pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Instant horario;
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "profissional_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Profissional profissional;

	@ManyToMany
	@JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos = new ArrayList<>();

	private Double valorTotal;
	
	private Double comissao;
	
	private Integer pagamentoStatus;
	
	private Integer formaPagamento;
	
	private Instant momentoPagamento;
	
	
	public Pedido() {

	}

	public Pedido(Long id, Instant horario, String descricao, Cliente cliente, Profissional profissional,
			List<Produto> produtos, Double valorTotal, Double comissao, PagamentoStatus pagamentoStatus, FormaPagamento formaPagamento) {
		super();
		this.id = id;
		this.horario = horario;
		this.descricao = descricao;
		this.cliente = cliente;
		this.profissional = profissional;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.comissao = comissao;
		setPagamentoStatus(pagamentoStatus);
		setFormaPagamento(formaPagamento);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	

}
