package com.jeanpiress.brFinanceiro.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.repositories.CredorRepository;
import com.jeanpiress.brFinanceiro.services.CredorService;

@RunWith(MockitoJUnitRunner.class)
public class CredorServiceTest {

	@InjectMocks
	CredorService service;

	@Mock
	CredorRepository repository;

	Credor credor;

	Credor credorAlterado;
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		credor = new Credor(1L, "loreal", "produtos");
		credorAlterado = new Credor(1L, "Forma", "produtos Loreal");

	}

	@Test
	public void deveBuscarTodosCredors() {
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(credor));

		List<Credor> credors = service.buscar();

		Assertions.assertEquals(credors, Collections.singletonList(credor));

		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void debeBuscarCredorPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(credor));

		Optional<Credor> credorOptional = service.buscarPorId(1L);

		Assertions.assertEquals(credorOptional.get(), credor);
		verify(repository).findById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveAdicionarUmCredor() {
		Mockito.when(repository.save(credor)).thenReturn(credor);

		Credor credorSalvo = service.cadastrar(credor);

		Assertions.assertEquals(credorSalvo, credor);
		verify(repository).save(credor);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmCredor() {
		Mockito.when(repository.save(credor)).thenReturn(credor);
		Mockito.when(repository.getReferenceById(1L)).thenReturn(credor);

		Credor credor = service.alterar(1L, credorAlterado);

		Assertions.assertEquals(credor.getId(), 1L);
		Assertions.assertEquals(credor.getNome(), credorAlterado.getNome());
		Assertions.assertEquals(credor.getSeguimento(), credorAlterado.getSeguimento());
		

		verify(repository).save(credor);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveDeletarUmCredor() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
	}

}