package com.jeanpiress.produtos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.produtos.entities.Produto;
import com.jeanpiress.produtos.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;



	public List<Produto> buscar() {
		return repository.findAll();
		
	}



	public Optional<Produto> buscarPorId(Long id) {
		Optional<Produto> profissional = repository.findById(id);
		return profissional;
	}



	public Produto cadastrar(Produto obj) {
		return repository.save(obj);
		
	}



	public Produto alterar(Long id, Produto obj) {
		Produto profissional = repository.getReferenceById(id);
		update(profissional, obj); 
		return repository.save(profissional);
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
