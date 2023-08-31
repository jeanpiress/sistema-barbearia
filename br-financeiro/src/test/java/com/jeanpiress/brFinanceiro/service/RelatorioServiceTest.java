package com.jeanpiress.brFinanceiro.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.Instant;
import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jeanpiress.brFinanceiro.entities.Pedido;
import com.jeanpiress.brFinanceiro.entities.Produto;
import com.jeanpiress.brFinanceiro.entities.Profissional;
import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.feignclients.PedidoFeignClient;
import com.jeanpiress.brFinanceiro.feignclients.ProfissionalFeignClient;
import com.jeanpiress.brFinanceiro.services.RelatorioService;

@RunWith(MockitoJUnitRunner.class)
public class RelatorioServiceTest {

	@InjectMocks
	RelatorioService service;
	
	
	@Mock
	ProfissionalFeignClient profissionalFeignClient;
	
	@Mock
	PedidoFeignClient pedidoFeignClient;
	
	Salario salarioJean;
	
	Profissional profissional;
	
	ResponseEntity<Optional<Profissional>> profissionalResponse;
	
	ResponseEntity<Double> comissaoResponse;
	
	ResponseEntity<List<Pedido>> pedidosResponse;
	
	Pedido pedido1;
	
	Pedido pedido2;
	
	String inicio;
	
	String fim;
	
	Instant horario;
	
	Produto corte;
	
	Produto barba;
	
	List<Produto> produtos = new ArrayList<>();
	
	List<Pedido> pedidos = new ArrayList<>();
	
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		profissional = new Profissional(1L, "Jean", "(34)999708382", null, 0.0);
		
		salarioJean = new Salario("Jean", 0.0, 200.0);
		
		profissionalResponse = ResponseEntity.status(HttpStatus.OK).body(Optional.of(profissional));
		
		comissaoResponse = ResponseEntity.status(HttpStatus.OK).body(200.0);
		
		pedidosResponse = ResponseEntity.status(HttpStatus.OK).body(pedidos);
	
		horario = Instant.parse("2023-08-28T10:00:00Z");
		
		pedido1 = new Pedido(horario, "corte e barba", profissional, produtos, 90.0, 45.0, null, null);
		
		pedido2 = new Pedido(horario, "corte e barba", profissional, produtos, 90.0, 45.0, null, null);
		
		corte = new Produto("corte", 45.0, null, 50.0, false, null);
		
		barba = new Produto("barba", 45.0, null, 50.0, false, null);
		
				
		produtos.add(corte);
		produtos.add(barba);
		
		pedidos.add(pedido1);
		pedidos.add(pedido2);
		
		inicio = "2023-08-27";
		
		fim = "2023-08-28";
	}
	
	
	@Test
	public void deveBuscarSalarioDoProfissionalPorId() {
		Mockito.when(profissionalFeignClient.buscarPorId(1L)).thenReturn(profissionalResponse);
		Mockito.when(pedidoFeignClient.verificarComissaoPorPeriodo(1L, inicio , fim )).thenReturn(comissaoResponse);
		
		
               
       Salario salario = service.salarioFuncionarioPorPeriodo(1L, inicio, fim);
       
       Assertions.assertEquals(salario.getNomeProfissional(), salarioJean.getNomeProfissional());
       Assertions.assertEquals(salario.getSalarioFixo(), salarioJean.getSalarioFixo());
       Assertions.assertEquals(salario.getComissao(), salarioJean.getComissao());
       
       verify(profissionalFeignClient).buscarPorId(1L);
       verify(pedidoFeignClient).verificarComissaoPorPeriodo(1L, inicio, fim);
       verifyNoMoreInteractions(profissionalFeignClient, pedidoFeignClient);
       

       
	}
	
	@Test
	public void deveBuscarFaturamentoDoMesPassandoMesEAno() {
		Mockito.when(pedidoFeignClient.getPedido()).thenReturn(pedidosResponse);
		
		Double faturamento = service.faturamentoPorMes(2023, 8);
		
		Assertions.assertEquals(faturamento, 180.0);
		
		verify(pedidoFeignClient).getPedido();
		verifyNoMoreInteractions(pedidoFeignClient);
	}
	
	
}
