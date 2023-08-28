package com.jeanpiress.brFinanceiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entities.GastoExtraordinario;
import com.jeanpiress.brFinanceiro.repositories.GastoExtraordinarioRepository;

@Service
public class GastoExtraordinarioService {

	@Autowired
	GastoExtraordinarioRepository repository;

	

	public List<GastoExtraordinario> buscar() {
		List<GastoExtraordinario> gastoExtraordinarios = repository.findAll();
		return gastoExtraordinarios;
	}

	
	public Optional<GastoExtraordinario> buscarPorId(Long id) {
		Optional<GastoExtraordinario> gastoExtraordinario = repository.findById(id);
		return gastoExtraordinario;
	}



	public GastoExtraordinario cadastrar(GastoExtraordinario obj) {
		return repository.save(obj);
		
	}



	public GastoExtraordinario alterar(Long id, GastoExtraordinario obj) {
		GastoExtraordinario gastoExtraordinario = repository.getReferenceById(id);
		update(gastoExtraordinario, obj); 
		return repository.save(gastoExtraordinario);
	}



	private void update(GastoExtraordinario base, GastoExtraordinario alterado) {
		base.setCredor(alterado.getCredor());
		base.setValor(alterado.getValor());
		base.setDataPagamento(alterado.getDataPagamento());
		
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	
	
	

}
