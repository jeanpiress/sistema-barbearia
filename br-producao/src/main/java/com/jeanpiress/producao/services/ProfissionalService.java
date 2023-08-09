package com.jeanpiress.producao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.producao.entities.Profissional;
import com.jeanpiress.producao.repository.ProfissionalRepository;

@Service
public class ProfissionalService {

	@Autowired
	ProfissionalRepository repository;

	

	public List<Profissional> buscar() {
		List<Profissional> pedidos = repository.findAll();
		return pedidos;
	}

	public Optional<Profissional> buscarPorId(Long id) {
		Optional<Profissional> Profissionais = repository.findById(id);
		return Profissionais;
	}



	public Profissional cadastrar(Profissional obj) {
		return repository.save(obj);
		
	}



	public Profissional alterar(Long id, Profissional obj) {
		Profissional Profissionais = repository.getReferenceById(id);
		update(Profissionais, obj); 
		return repository.save(Profissionais);
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
