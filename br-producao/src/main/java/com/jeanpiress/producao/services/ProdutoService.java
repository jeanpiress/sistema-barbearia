package com.jeanpiress.producao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.producao.entities.Pedido;
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.repository.ProdutoRepository;

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
	
	public Produto alterar(Long id, Produto obj) {
		Produto produto = repository.getReferenceById(id);
		update(produto, obj); 
		return repository.save(produto);
	}



	private void update(Produto base, Produto alterado) {
		base.setNome(alterado.getNome());
		base.setValor(alterado.getValor());
		base.setCusto(alterado.getCusto());
		base.setComissao(alterado.getComissao());
		base.setTemEstoque(alterado.isTemEstoque());
		base.setEstoque(alterado.getEstoque());
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	

}
