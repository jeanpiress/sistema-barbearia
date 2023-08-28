package com.jeanpiress.brFinanceiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.repositories.CredorRepository;

@Service
public class CredorService {

	@Autowired
	CredorRepository repository;

	

	public List<Credor> buscar() {
		List<Credor> credors = repository.findAll();
		return credors;
	}

	
	public Optional<Credor> buscarPorId(Long id) {
		Optional<Credor> credor = repository.findById(id);
		return credor;
	}



	public Credor cadastrar(Credor obj) {
		return repository.save(obj);
		
	}



	public Credor alterar(Long id, Credor obj) {
		Credor credor = repository.getReferenceById(id);
		update(credor, obj); 
		return repository.save(credor);
	}



	private void update(Credor base, Credor alterado) {
		base.setNome(alterado.getNome());
		base.setSeguimento(alterado.getSeguimento());
				
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	
	
	

}
