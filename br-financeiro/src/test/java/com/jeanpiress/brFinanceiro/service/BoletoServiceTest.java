package com.jeanpiress.brFinanceiro.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.Instant;
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

import com.jeanpiress.brFinanceiro.entities.Boleto;
import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.repositories.BoletoRepository;
import com.jeanpiress.brFinanceiro.services.BoletoService;

@RunWith(MockitoJUnitRunner.class)
public class BoletoServiceTest {

	@InjectMocks
	BoletoService service;

	@Mock
	BoletoRepository repository;

	Boleto boleto;

	Boleto boletoAlterado;
	
	Credor loreal;
	
	Instant dataPagamento;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		loreal = new Credor(1L, "loreal", "produtos");
		dataPagamento = Instant.parse("2023-08-28T00:00:00Z");
		boleto = new Boleto(1L, loreal, 350.0, dataPagamento, PagamentoStatus.APAGAR);
		boletoAlterado = new Boleto(1L, loreal, 380.0, dataPagamento, PagamentoStatus.APAGAR);

	}

	@Test
	public void deveBuscarTodosBoletos() {
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(boleto));

		List<Boleto> boletos = service.buscar();

		Assertions.assertEquals(boletos, Collections.singletonList(boleto));

		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void debeBuscarBoletoPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(boleto));

		Optional<Boleto> boletoOptional = service.buscarPorId(1L);

		Assertions.assertEquals(boletoOptional.get(), boleto);
		verify(repository).findById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveAdicionarUmBoleto() {
		Mockito.when(repository.save(boleto)).thenReturn(boleto);

		Boleto boletoSalvo = service.cadastrar(boleto);

		Assertions.assertEquals(boletoSalvo, boleto);
		verify(repository).save(boleto);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmBoleto() {
		Mockito.when(repository.save(boleto)).thenReturn(boleto);
		Mockito.when(repository.getReferenceById(1L)).thenReturn(boleto);

		Boleto boleto = service.alterar(1L, boletoAlterado);

		Assertions.assertEquals(boleto.getId(), 1L);
		Assertions.assertEquals(boleto.getValor(), boletoAlterado.getValor());
		

		verify(repository).save(boleto);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveDeletarUmBoleto() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
	}

}