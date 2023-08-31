package com.jeanpiress.producao.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.producao.entities.Cliente;
import com.jeanpiress.producao.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	

	public List<Cliente> buscar() {
		List<Cliente> clientes = repository.findAll();
		return clientes;
	}

	
	public Optional<Cliente> buscarPorId(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente;
	}



	public Cliente cadastrar(Cliente obj) {
		return repository.save(obj);
		
	}



	public Cliente alterar(Long id, Cliente obj) {
		Cliente cliente = repository.getReferenceById(id);
		update(cliente, obj); 
		return repository.save(cliente);
	}



	private void update(Cliente base, Cliente alterado) {
		base.setNome(alterado.getNome());
		base.setCelular(alterado.getCelular());
		base.setNascimento(alterado.getNascimento());
		base.setUltimaVisita(alterado.getUltimaVisita());
		base.setPontos(alterado.getPontos());
		base.setPrevisaoRetorno(alterado.getPrevisaoRetorno());
		base.setObservacao(alterado.getObservacao());
		base.setEndereco(alterado.getEndereco());
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	
	public Instant atualizarPrevisaoRetorno(Long id) {
		Cliente cliente = repository.getReferenceById(id);
		
		Integer diasParaVoltar = cliente.getDiasParaVoltar();
		Instant ultimaVisita = cliente.getUltimaVisita();
		Instant previsaoRetorno = ultimaVisita.plus(diasParaVoltar,  ChronoUnit.DAYS);
		
		
		
		return previsaoRetorno;
	}
	
	
	
	

}
