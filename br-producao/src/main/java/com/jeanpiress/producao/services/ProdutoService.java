package com.jeanpiress.producao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.producao.entities.CategoriaProduto;
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;
	
	@Autowired
	CategoriaProdutoService categoriaService;

	

	public List<Produto> buscar() {
		List<Produto> pedidos = repository.findAll();
		return pedidos;
	}
	
	public Optional<Produto> buscarPorId(Long id) {
		Optional<Produto> produto = repository.findById(id);
		return produto;
	}

	public Produto cadastrar(Produto pedido) {
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
		base.setCategoria(alterado.getCategoria());
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Produto adicionarCategoria(Long id, Long categoriaId) {
	
		Produto produto = buscarPorId(id).get();
		CategoriaProduto categoria = categoriaService.buscarPorId(categoriaId).get();
		produto.setCategoria(categoria);
				
		return repository.save(produto);
	}
	

}
