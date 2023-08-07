package com.jeanpiress.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.pedidos.entities.Pedido;
import com.jeanpiress.pedidos.entities.Produto;
import com.jeanpiress.pedidos.repository.PedidoRepository;
import com.jeanpiress.pedidos.repository.ProdutoRepository;

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
		Optional<Pedido> cliente = repository.findById(id);
		return cliente;
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
		Produto produto = produtoService.buscarPorId(produtoId);
		List<Produto> produtos = pedido.getProdutos();
		produtos.add(produto);
		pedido.setProdutos(produtos);
		atulaizarValor(pedido);	
		
		return repository.save(pedido);
	}
	
	public Pedido atulaizarValor(Pedido pedido) {
		List<Produto> produtos = pedido.getProdutos();
	    Double total = pedido.total(produtos);
	    pedido.setValorTotal(total);
	    
	    return pedido;
	}

}
