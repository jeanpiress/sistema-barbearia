package com.jeanpiress.producao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.producao.entities.Pedido;
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repository;
	
	@Autowired
	ProdutoService produtoService;
	
		
	public List<Pedido> buscar() {
		List<Pedido> pedidos = repository.findAll();
		return pedidos;
	}

	public Optional<Pedido> buscarPorId(Long id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido;
	}



	public Pedido cadastrar(Pedido obj) {
		return repository.save(obj);
		
	}



	public Pedido alterar(Long id, Pedido obj) {
		Pedido pedido = repository.getReferenceById(id);
		update(pedido, obj); 
		return repository.save(pedido);
	}



	private void update(Pedido base, Pedido alterado) {
		base.setCliente(alterado.getCliente());
		base.setDescricao(alterado.getDescricao());
		base.setHorario(alterado.getHorario());
		base.setProfissional(alterado.getProfissional());
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Pedido adicionarProduto(Long id, Long produtoId) {
		Pedido pedido = buscarPorId(id).get();
		Produto produto = produtoService.buscarPorId(produtoId).get();
		List<Produto> produtos = pedido.getProdutos();
		produtos.add(produto);
		pedido.setProdutos(produtos);
		atulaizarValor(pedido);	
		
		return repository.save(pedido);
	}
	
	
	public Pedido removerProduto(Long id, Long produtoId) {
		Pedido pedido = buscarPorId(id).get();
		Produto produto = produtoService.buscarPorId(produtoId).get();
		List<Produto> produtos = pedido.getProdutos();
		produtos.remove(produto);
		pedido.setProdutos(produtos);
		atulaizarValor(pedido);	
		
		return repository.save(pedido);
	}
	
	public Pedido atulaizarValor(Pedido pedido) {
		Double total = pedido.total();
	    pedido.setValorTotal(total);
	    
	    return pedido;
	}
	
	public Double comissao(Long id) {
		Pedido pedido = buscarPorId(id).get();
		Double comissao = pedido.comissaoPaga();
		return comissao;
	}

}
