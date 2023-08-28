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

	GastoFixo gastoFixoAlterado;
	
	Credor credor;
	
	

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		credor = new Credor(1L, "Imobiliaria", "Aluguel");
		gastoFixo = new GastoFixo(1L, "Aluguel", 3870.0, credor, "Aluguel", 20, true, PagamentoStatus.APAGAR);
		gastoFixoAlterado = new GastoFixo(1L, "Aluguel", 4000.0, credor, "Aluguel do imovel", 15, true, PagamentoStatus.APAGAR);

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
		Assertions.assertEquals(gastoFixo.getDiaVencimento(), gastoFixoAlterado.getDiaVencimento());
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

}