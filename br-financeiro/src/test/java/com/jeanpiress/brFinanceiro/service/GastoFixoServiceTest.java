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

import com.jeanpiress.brFinanceiro.entities.GastoFixo;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.repositories.GastoFixoRepository;
import com.jeanpiress.brFinanceiro.services.GastoFixoService;

@RunWith(MockitoJUnitRunner.class)
public class GastoFixoServiceTest {

	@InjectMocks
	GastoFixoService service;

	@Mock
	GastoFixoRepository repository;

	GastoFixo gastoFixo;
	
	GastoFixo gastoFixo2;
	
	GastoFixo gastoFixo3;

	GastoFixo gastoFixoAlterado;
	
	List<GastoFixo> gastosFixos = new ArrayList<>();
	
	Instant dataFutura;
	
	Instant dataPassada;
	

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		dataFutura = Instant.parse("2023-08-28T00:00:00Z");
		dataPassada = Instant.parse("2023-07-28T00:00:00Z");
		gastoFixo = new GastoFixo(1L, "Aluguel", 3870.0, "Aluguel", dataFutura, true, PagamentoStatus.APAGAR, 23);
		gastoFixo2 = new GastoFixo(2L, "Energia", 3870.0, "Energia", dataFutura, true, PagamentoStatus.APAGAR, 23);
		gastoFixo3 = new GastoFixo(3L, "Energia", 3870.0, "Energia", dataPassada, true, PagamentoStatus.PAGO, 23);
		gastoFixoAlterado = new GastoFixo(1L, "Aluguel", 4000.0, "Aluguel do imovel", dataFutura, true, PagamentoStatus.APAGAR, 23);
		
		gastosFixos.add(gastoFixo);
		gastosFixos.add(gastoFixo2);
		gastosFixos.add(gastoFixo3);
		

	}

	@Test
	public void deveBuscarTodosGastoFixos() {
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(gastoFixo));

		List<GastoFixo> gastoFixos = service.buscar();

		Assertions.assertEquals(gastoFixos, Collections.singletonList(gastoFixo));

		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void debeBuscarGastoFixoPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(gastoFixo));

		Optional<GastoFixo> gastoFixoOptional = service.buscarPorId(1L);

		Assertions.assertEquals(gastoFixoOptional.get(), gastoFixo);
		verify(repository).findById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveAdicionarUmGastoFixo() {
		Mockito.when(repository.save(gastoFixo)).thenReturn(gastoFixo);

		GastoFixo gastoFixoSalvo = service.cadastrar(gastoFixo);

		Assertions.assertEquals(gastoFixoSalvo, gastoFixo);
		verify(repository).save(gastoFixo);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmGastoFixo() {
		Mockito.when(repository.save(gastoFixo)).thenReturn(gastoFixo);
		Mockito.when(repository.getReferenceById(1L)).thenReturn(gastoFixo);

		GastoFixo gastoFixo = service.alterar(1L, gastoFixoAlterado);

		Assertions.assertEquals(gastoFixo.getId(), 1L);
		Assertions.assertEquals(gastoFixo.getValor(), gastoFixoAlterado.getValor());
		Assertions.assertEquals(gastoFixo.getNome(), gastoFixoAlterado.getNome());
		Assertions.assertEquals(gastoFixo.getMotivo(), gastoFixoAlterado.getMotivo());
		

		verify(repository).save(gastoFixo);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveDeletarUmGastoFixo() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	public void DeveBuscarGastosDoMes() {
		Mockito.when(service.buscar()).thenReturn(gastosFixos);
		
		List<GastoFixo> gastosMes = service.buscarTodosGastosFixosAtivosMes(2023, 8);
		
		Assertions.assertTrue(gastosMes.contains(gastoFixo));
		Assertions.assertTrue(gastosMes.contains(gastoFixo2));
		Assertions.assertFalse(gastosMes.contains(gastoFixo3));
		
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
		
	}

}