package com.jeanpiress.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.pedidos.entities.Produto;
import com.jeanpiress.pedidos.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	

	public List<Produto> buscar() {
		List<Produto> pedidos = repository.findAll();
		return pedidos;
	}
	
	public Produto buscarPorId(Long id) {
		Optional<Produto> produto = repository.findById(id);
		return produto.get();
	}

	public Produto adicionar(Produto pedido) {
		return repository.save(pedido);
	}
	
	

}
