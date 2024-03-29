package com.jeanpiress.brFinanceiro.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.Instant;
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

import com.jeanpiress.brFinanceiro.entities.GastoExtraordinario;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.repositories.GastoExtraordinarioRepository;
import com.jeanpiress.brFinanceiro.services.GastoExtraordinarioService;

@RunWith(MockitoJUnitRunner.class)
public class GastoExtraordinarioServiceTest {

	@InjectMocks
	GastoExtraordinarioService service;

	@Mock
	GastoExtraordinarioRepository repository;

	GastoExtraordinario gastoExtraordinario;
	
	GastoExtraordinario gastoExtraordinario2;

	GastoExtraordinario gastoExtraordinarioAlterado;
	
	Instant dataPagamento;

	Instant dataPagamentoPassada;
	
	List<GastoExtraordinario> gastosEstraordinarios = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		dataPagamento = Instant.parse("2023-08-28T00:00:00Z");
		dataPagamentoPassada = Instant.parse("2023-07-28T00:00:00Z");
		gastoExtraordinario = new GastoExtraordinario(1L, 350.0, dataPagamento, PagamentoStatus.APAGAR);
		gastoExtraordinario2 = new GastoExtraordinario(2L, 350.0, dataPagamentoPassada, PagamentoStatus.PAGO);
		gastoExtraordinarioAlterado = new GastoExtraordinario(1L, 380.0, dataPagamento, PagamentoStatus.APAGAR);
		
		gastosEstraordinarios.add(gastoExtraordinario);
		gastosEstraordinarios.add(gastoExtraordinario2);

	}

	@Test
	public void deveBuscarTodosGastoExtraordinarios() {
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(gastoExtraordinario));

		List<GastoExtraordinario> gastoExtraordinarios = service.buscar();

		Assertions.assertEquals(gastoExtraordinarios, Collections.singletonList(gastoExtraordinario));

		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void debeBuscarGastoExtraordinarioPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(gastoExtraordinario));

		Optional<GastoExtraordinario> gastoExtraordinarioOptional = service.buscarPorId(1L);

		Assertions.assertEquals(gastoExtraordinarioOptional.get(), gastoExtraordinario);
		verify(repository).findById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveAdicionarUmGastoExtraordinario() {
		Mockito.when(repository.save(gastoExtraordinario)).thenReturn(gastoExtraordinario);

		GastoExtraordinario gastoExtraordinarioSalvo = service.cadastrar(gastoExtraordinario);

		Assertions.assertEquals(gastoExtraordinarioSalvo, gastoExtraordinario);
		verify(repository).save(gastoExtraordinario);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmGastoExtraordinario() {
		Mockito.when(repository.save(gastoExtraordinario)).thenReturn(gastoExtraordinario);
		Mockito.when(repository.getReferenceById(1L)).thenReturn(gastoExtraordinario);

		GastoExtraordinario gastoExtraordinario = service.alterar(1L, gastoExtraordinarioAlterado);

		Assertions.assertEquals(gastoExtraordinario.getId(), 1L);
		Assertions.assertEquals(gastoExtraordinario.getValor(), gastoExtraordinarioAlterado.getValor());
		

		verify(repository).save(gastoExtraordinario);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveDeletarUmGastoExtraordinario() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void DeveBuscarGastosExtraordinariosDoMes() {
		Mockito.when(service.buscar()).thenReturn(gastosEstraordinarios);
		
		List<GastoExtraordinario> gastosExtraordinariosMes = service.buscarTodosGastosPorMes(2023, 8);
		
		Assertions.assertTrue(gastosExtraordinariosMes.contains(gastoExtraordinario));
		Assertions.assertFalse(gastosExtraordinariosMes.contains(gastoExtraordinario2));
		
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
		
	}
}