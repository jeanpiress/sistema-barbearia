package com.jeanpiress.producao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.producao.entities.CategoriaProduto;
import com.jeanpiress.producao.repository.CategoriaProdutoRepository;

@Service
public class CategoriaProdutoService {

	@Autowired
	CategoriaProdutoRepository repository;
	
	
	

	public List<CategoriaProduto> buscar() {
		List<CategoriaProduto> categorias = repository.findAll();
		return categorias;
	}

	
	public Optional<CategoriaProduto> buscarPorId(Long id) {
		Optional<CategoriaProduto> categoria = repository.findById(id);
		return categoria;
	}



	public CategoriaProduto cadastrar(CategoriaProduto obj) {
		return repository.save(obj);
		
	}



	public CategoriaProduto alterar(Long id, CategoriaProduto obj) {
		CategoriaProduto categoria = repository.getReferenceById(id);
		update(categoria, obj); 
		return repository.save(categoria);
	}



	private void update(CategoriaProduto base, CategoriaProduto alterado) {
		base.setNome(alterado.getNome());
		
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
		
	
	

}
