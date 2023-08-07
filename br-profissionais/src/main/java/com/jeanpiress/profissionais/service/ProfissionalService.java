package com.jeanpiress.profissionais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.profissionais.entities.Profissional;
import com.jeanpiress.profissionais.repositories.ProfissionalRepository;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository repository;



	public List<Profissional> buscar() {
		return repository.findAll();
		
	}



	public Optional<Profissional> buscarPorId(Long id) {
		Optional<Profissional> profissional = repository.findById(id);
		return profissional;
	}



	public Profissional cadastrar(Profissional obj) {
		return repository.save(obj);
		
	}



	public Profissional alterar(Long id, Profissional obj) {
		Profissional profissional = repository.getReferenceById(id);
		update(profissional, obj); 
		return repository.save(profissional);
	}



	private void update(Profissional base, Profissional alterado) {
		base.setNome(alterado.getNome());
		base.setCelular(alterado.getCelular());
		base.setNascimento(alterado.getNascimento());
		base.setSalarioFixo(alterado.getSalarioFixo());
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}



}
