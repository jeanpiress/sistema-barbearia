package com.jeanpiress.producao.service;

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

import com.jeanpiress.producao.entities.Profissional;
import com.jeanpiress.producao.repository.ProfissionalRepository;
import com.jeanpiress.producao.services.ProfissionalService;

@RunWith(MockitoJUnitRunner.class)
public class ProfissionalServiceTest {

	@InjectMocks
	ProfissionalService service;

	@Mock
	ProfissionalRepository repository;

	Profissional profissional;

	Profissional profissionalAlterado;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		profissional = new Profissional(1L, "Jean", "(34)992768382", null, 0.0, null);
		profissionalAlterado = new Profissional(1L, "Jean Pires", "(34)999708382", null, 0.0, null);

	}

	@Test
	public void deveBuscarTodosProfissionals() {
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(profissional));

		List<Profissional> profissionals = service.buscar();

		Assertions.assertEquals(profissionals, Collections.singletonList(profissional));

		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void debeBuscarProfissionalPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(profissional));

		Optional<Profissional> profissionalOptional = service.buscarPorId(1L);

		Assertions.assertEquals(profissionalOptional.get(), profissional);
		verify(repository).findById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveAdicionarUmProfissional() {
		Mockito.when(repository.save(profissional)).thenReturn(profissional);

		Profissional profissionalSalvo = service.cadastrar(profissional);

		Assertions.assertEquals(profissionalSalvo, profissional);
		verify(repository).save(profissional);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmProfissional() {
		Mockito.when(repository.save(profissional)).thenReturn(profissional);
		Mockito.when(repository.getReferenceById(1L)).thenReturn(profissional);

		Profissional profissional = service.alterar(1L, profissionalAlterado);

		Assertions.assertEquals(profissional.getId(), 1L);
		Assertions.assertEquals(profissional.getNome(), profissionalAlterado.getNome());
		Assertions.assertEquals(profissional.getCelular(), profissionalAlterado.getCelular());

		verify(repository).save(profissional);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveDeletarUmProfissional() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
	}

}