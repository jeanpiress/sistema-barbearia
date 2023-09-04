package com.jeanpiress.brFinanceiro.service;

import static org.mockito.Mockito.times;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jeanpiress.brFinanceiro.entities.Cliente;
import com.jeanpiress.brFinanceiro.entities.Pedido;
import com.jeanpiress.brFinanceiro.entities.Produto;
import com.jeanpiress.brFinanceiro.entities.Profissional;
import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.feignclients.ClienteFeignClient;
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
	
	@Mock
	ClienteFeignClient clienteFeignClient;
	
	Salario salarioJean;
	
	Profissional profissional;
	
	Cliente cliente;
	
	ResponseEntity<Optional<Profissional>> profissionalResponse;
	
	ResponseEntity<List<Profissional>> profissionaisResponse;
	
	ResponseEntity<List<Cliente>> clienteResponse;
		
	ResponseEntity<Double> comissaoResponse;
	
	ResponseEntity<List<Pedido>> pedidosResponse;
	
	Pedido pedido1;
	
	Pedido pedido2;
	
	String inicio;
	
	String fim;
	
	String inicioProximoMes;
	
	String fimProximoMes;
	
	Instant horario;
	
	Instant retorno;
	
	Produto corte;
	
	Produto barba;
	
	List<Produto> produtos = new ArrayList<>();
	
	List<Pedido> pedidos = new ArrayList<>();
	
	List<Cliente> clientes = new ArrayList<>();
	
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		retorno = Instant.parse("2023-09-28T10:00:00Z");
		
		profissional = new Profissional(1L, "Jean", "(34)999708382", null, 0.0);
		
		salarioJean = new Salario("Jean", 0.0, 200.0);
		
		cliente = new Cliente(1L, "Carol", "(34)999708382", null, horario, null, 30, retorno, null);
		
		profissionalResponse = ResponseEntity.status(HttpStatus.OK).body(Optional.of(profissional));
		
		profissionaisResponse = ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(profissional));
		
		clienteResponse = ResponseEntity.status(HttpStatus.OK).body(clientes);
		
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
		
		clientes.add(cliente);
		
		inicio = "2023-08-27";
		
		fim = "2023-08-28";
		
		inicioProximoMes = "2023-09-25";
		
		fimProximoMes = "2023-09-30";
		
		
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
	
	
	@Test
	public void deveRetornarNuloFaturamentoDoMesPassandoMesSemFaturamento() {
		Mockito.when(pedidoFeignClient.getPedido()).thenReturn(pedidosResponse);
		
		Double faturamento = service.faturamentoPorMes(2023, 9);
		
		Assertions.assertEquals(faturamento, 0.0);
		
		verify(pedidoFeignClient).getPedido();
		verifyNoMoreInteractions(pedidoFeignClient);
	}
	
	@Test
	public void deveRetornarNuloFaturamentoDoMesPassandoAnoSemFaturamento() {
		Mockito.when(pedidoFeignClient.getPedido()).thenReturn(pedidosResponse);
		
		Double faturamento = service.faturamentoPorMes(2024, 8);
		
		Assertions.assertEquals(faturamento, 0.0);
		
		verify(pedidoFeignClient).getPedido();
		verifyNoMoreInteractions(pedidoFeignClient);
	}
	
	@Test
	public void deveBuscarSalarioDeUmFuncionarioExpecificoMes() {
		Mockito.when(profissionalFeignClient.buscarPorId(1L)).thenReturn(profissionalResponse);
		Mockito.when(pedidoFeignClient.verificarComissaoProfissionalMes(1L, 2023, 8)).thenReturn(comissaoResponse);
		
		Salario salario = service.salarioFuncionarioMes(1L, 2023, 8);
		
		Assertions.assertEquals(salario.getNomeProfissional(), profissionalResponse.getBody().get().getNome());
		Assertions.assertEquals(salario.getSalarioFixo(), profissionalResponse.getBody().get().getSalarioFixo());
		Assertions.assertEquals(salario.getComissao(), comissaoResponse.getBody());
		
		
		verify(profissionalFeignClient).buscarPorId(1L);
		verify(pedidoFeignClient).verificarComissaoProfissionalMes(1L, 2023, 8);
		verifyNoMoreInteractions(profissionalFeignClient, pedidoFeignClient);
	}
	
	@Test
	public void deveBuscarSalarioDeTodosFuncionariosMes() {
		Mockito.when(profissionalFeignClient.buscar()).thenReturn(profissionaisResponse);
		Mockito.when(profissionalFeignClient.buscarPorId(1L)).thenReturn(profissionalResponse);
		Mockito.when(pedidoFeignClient.verificarComissaoProfissionalMes(1L, 2023 , 8 )).thenReturn(comissaoResponse);
		
		
		List<Salario> salarios = service.salarioTodosProfissionaisMes(2023, 8);
		
		Assertions.assertEquals(salarios.get(0).getNomeProfissional(), salarioJean.getNomeProfissional());
		
		verify(profissionalFeignClient).buscar();
		verify(profissionalFeignClient).buscarPorId(1L);
		verify(pedidoFeignClient).verificarComissaoProfissionalMes(1L, 2023, 8);
		verifyNoMoreInteractions(profissionalFeignClient, pedidoFeignClient);
		
	}
	
	@Test
	public void deveBuscarSalarioDeTodosFuncionariosPorPeriodo() {
		Mockito.when(profissionalFeignClient.buscar()).thenReturn(profissionaisResponse);
		Mockito.when(profissionalFeignClient.buscarPorId(1L)).thenReturn(profissionalResponse);
		Mockito.when(pedidoFeignClient.verificarComissaoPorPeriodo(1L, inicio , fim )).thenReturn(comissaoResponse);
		
		
		List<Salario> salarios = service.salarioTodosProfissionaisPeriodo(inicio, fim);
		
		Assertions.assertEquals(salarios.get(0).getNomeProfissional(), salarioJean.getNomeProfissional());
		
		verify(profissionalFeignClient).buscar();
		verify(profissionalFeignClient).buscarPorId(1L);
		verify(pedidoFeignClient).verificarComissaoPorPeriodo(1L, inicio, fim);
		verifyNoMoreInteractions(profissionalFeignClient, pedidoFeignClient);
		
	}
	
	@Test
	public void deveBuscarClientesQueEstaoNoDiaDeVoltar() {
		Mockito.when(clienteFeignClient.buscar()).thenReturn(clienteResponse);
	
			List<Cliente> clientesVoltar = service.clientesNoDiaDeVoltar(inicioProximoMes, fimProximoMes);
		
	
	    Assertions.assertEquals(clientesVoltar.get(0) , clientes.get(0));
			
	    verify(clienteFeignClient).buscar();
	    verifyNoMoreInteractions(clienteFeignClient);
		}
	
	
	@Test
	public void deveRetornarVazioListaClientesQueEstaoNoDiaDeVoltarSeNaoTiverClienteNestePeriodo() {
		Mockito.when(clienteFeignClient.buscar()).thenReturn(clienteResponse);
	
			List<Cliente> clientesVoltar1 = service.clientesNoDiaDeVoltar(fimProximoMes, fimProximoMes);
			List<Cliente> clientesVoltar2 = service.clientesNoDiaDeVoltar(inicioProximoMes, inicioProximoMes);
	
	    Assertions.assertTrue(clientesVoltar1.isEmpty());
	    Assertions.assertTrue(clientesVoltar2.isEmpty());
	    
	    
	    verify(clienteFeignClient, times(2)).buscar();
	    verifyNoMoreInteractions(clienteFeignClient);
			
		}
	
	
}
