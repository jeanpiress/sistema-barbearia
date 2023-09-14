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

import com.jeanpiress.brFinanceiro.entities.Boleto;
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
	
	Boleto boleto2;

	Boleto boletoAlterado;
	
	Instant dataPagamentoAtual;
	
	Instant dataPagamentoPassada;
	
	List<Boleto> boletos = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		dataPagamentoAtual = Instant.parse("2023-08-28T00:00:00Z");
		dataPagamentoPassada = Instant.parse("2023-07-28T00:00:00Z");
		boleto = new Boleto(1L, 350.0, dataPagamentoAtual, PagamentoStatus.APAGAR);
		boleto2 = new Boleto(2L, 350.0, dataPagamentoPassada, PagamentoStatus.PAGO);
		boletoAlterado = new Boleto(1L, 380.0, dataPagamentoAtual, PagamentoStatus.APAGAR);
		
		boletos.add(boleto);
		boletos.add(boleto2);

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
	
	@Test
	public void DeveBuscarBoletosDoMes() {
		Mockito.when(service.buscar()).thenReturn(boletos);
		
		List<Boleto> boletosMes = service.buscarTodosBoletosPorMes(2023, 8);
		
		Assertions.assertTrue(boletosMes.contains(boleto));
		Assertions.assertFalse(boletosMes.contains(boleto2));
		
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
		
	}

}