package com.jeanpiress.brFinanceiro.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.Instant;
import java.util.Collections;

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
import com.jeanpiress.brFinanceiro.entities.CaixaMensal;
import com.jeanpiress.brFinanceiro.entities.GastoExtraordinario;
import com.jeanpiress.brFinanceiro.entities.GastoFixo;
import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.feignclients.ProfissionalFeignClient;
import com.jeanpiress.brFinanceiro.services.BoletoService;
import com.jeanpiress.brFinanceiro.services.CaixaMensalService;
import com.jeanpiress.brFinanceiro.services.GastoExtraordinarioService;
import com.jeanpiress.brFinanceiro.services.GastoFixoService;
import com.jeanpiress.brFinanceiro.services.RelatorioService;

@RunWith(MockitoJUnitRunner.class)
public class CaixaMensalServiceTest {

	@InjectMocks
	CaixaMensalService service;
	
	
	@Mock
	ProfissionalFeignClient profissionalFeignClient;
	
	@Mock
	RelatorioService relatorioService;
	
	@Mock
	private GastoExtraordinarioService gastoExtraodinarioService;
	
	@Mock
	private BoletoService boletoService;
	
	@Mock
	private GastoFixoService gastoFixosService;
	
	Salario salarioJean;
	
	Double faturamento;
	
	GastoExtraordinario gastoExtraordinario;
	
	Boleto boleto;
	
	GastoFixo gastoFixo;
	
	Integer ano;
	
	Integer mes;
	
	Instant horario;
	
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
				
		salarioJean = new Salario("Jean", 0.0, 200.0);
		
		gastoExtraordinario = new GastoExtraordinario(1L, null, 10.0, horario, PagamentoStatus.APAGAR);
		
		boleto = new Boleto(1L, null, 10.0, horario, PagamentoStatus.APAGAR);
		
		gastoFixo = new GastoFixo(1L, "agua", 30.0, null, "Agua", horario, true, PagamentoStatus.APAGAR);
			
		ano = 2020;
		
		mes = 8;
		
		faturamento = 400.0;
				
				
		
	}
	
	
	@Test
	public void deveBuscarCaixaMensalPorMes() {
		Mockito.when(relatorioService.faturamentoPorMes(ano, mes)).thenReturn(400.0);
		Mockito.when(relatorioService.salarioTodosProfissionaisMes(ano, mes)).thenReturn(Collections.singletonList(salarioJean));
		Mockito.when(gastoExtraodinarioService.buscarTodosGastosPorMes(ano, mes)).thenReturn(Collections.singletonList(gastoExtraordinario));
		Mockito.when(boletoService.buscarTodosBoletosPorMes(ano, mes)).thenReturn(Collections.singletonList(boleto));
		Mockito.when(gastoFixosService.buscarTodosGastosFixosPorMes(ano, mes)).thenReturn(Collections.singletonList(gastoFixo));
		
		CaixaMensal caixaMensal = service.buscarCaixaMensalPorMes(ano, mes);
		
		Assertions.assertEquals(caixaMensal.getFaturamento(), faturamento);
		Assertions.assertEquals(caixaMensal.getSalarios().get(0), salarioJean);
		Assertions.assertEquals(caixaMensal.getGastosExtraordinarios().get(0), gastoExtraordinario);
		Assertions.assertEquals(caixaMensal.getBoletos().get(0), boleto);
		Assertions.assertEquals(caixaMensal.getGastosFixos().get(0), gastoFixo);
		Assertions.assertEquals(caixaMensal.getLucro(), 150.0);
		
		verify(relatorioService).faturamentoPorMes(ano, mes);
		verify(relatorioService).salarioTodosProfissionaisMes(ano, mes);
		verify(gastoExtraodinarioService).buscarTodosGastosPorMes(ano, mes);
		verify(boletoService).buscarTodosBoletosPorMes(ano, mes);
		verify(gastoFixosService).buscarTodosGastosFixosPorMes(ano, mes);
		verifyNoMoreInteractions(relatorioService, gastoExtraodinarioService, boletoService, gastoFixosService);
		
		
	}
	
	@Test
	public void deveCalcularLucroCorretamente() {
	    //somando todos os gastos dá 250 e o faturamento 400
		Double lucro = service.calculoLucro(faturamento, Collections.singletonList(salarioJean), Collections.singletonList(gastoExtraordinario),
				Collections.singletonList(boleto), Collections.singletonList(gastoFixo));
	
		Assertions.assertEquals(lucro, 150.0);
	
	}
	
	
}
