package com.jeanpiress.clientes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.clientes.entites.Cliente;
import com.jeanpiress.clientes.respositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;



	public List<Cliente> buscar() {
		return repository.findAll();
		
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
		base.setObjservacao(alterado.getObservacao());
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}



}
