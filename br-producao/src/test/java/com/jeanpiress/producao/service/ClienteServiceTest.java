package com.jeanpiress.producao.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.jeanpiress.producao.entities.Cliente;
import com.jeanpiress.producao.repository.ClienteRepository;
import com.jeanpiress.producao.services.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

	@InjectMocks
	ClienteService service;

	@Mock
	ClienteRepository repository;
	
	Cliente cliente; 
	
	Cliente clienteAlterado;
	
	List<Cliente> clientes = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		cliente = new Cliente(1L, "Carol", "(34)999708382", null, null, 100, 30, null, "cabelo cacheado", null);
		clienteAlterado = new Cliente(null, "Carolina", "(34)999708382", null, null, 100, 30, null, "cabelo cacheado", null);
				
		clientes.add(cliente);
		
		
		
	}

	@Test
	public void deveBuscarTodosClientes() {
		Mockito.when(repository.findAll()).thenReturn(clientes);
		
        List<Cliente> clientes = service.buscar();
        
       Assertions.assertEquals(clientes, Collections.singletonList(cliente));
       
       verify(repository).findAll();
       verifyNoMoreInteractions(repository);

       
	}

	@Test
	public void debeBuscarClientePorId() {
		Mockito.when(repository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
		
		Optional<Cliente> clienteOptional = service.buscarPorId(cliente.getId());

		Assertions.assertEquals(clienteOptional.get(), cliente);
		verify(repository).findById(cliente.getId());
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveAdicionarUmCliente() {
		Mockito.when(repository.save(cliente)).thenReturn(cliente);
		
		Cliente clienteSalvo = service.cadastrar(cliente);

		Assertions.assertEquals(clienteSalvo, cliente);
		verify(repository).save(cliente);
		verifyNoMoreInteractions(repository);
	}
	
	
	@Test
	public void deveAlterrarUmCliente() {
		Mockito.when(repository.save(cliente)).thenReturn(cliente);
		Mockito.when(repository.getReferenceById(cliente.getId())).thenReturn(cliente);
		
		Cliente cliente = service.alterar(1L, clienteAlterado);

		Assertions.assertEquals(cliente.getId(), 1L);
		Assertions.assertEquals(cliente.getNome(), clienteAlterado.getNome());
		Assertions.assertEquals(cliente.getCelular(), clienteAlterado.getCelular());
		
		verify(repository).save(cliente);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveDeletarUmCliente() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
}

}