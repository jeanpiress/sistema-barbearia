package com.jeanpiress.brFinanceiro.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

import com.jeanpiress.brFinanceiro.entities.Profissional;
import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.feignclients.PedidoFeignClient;
import com.jeanpiress.brFinanceiro.feignclients.ProfissionalFeignClient;
import com.jeanpiress.brFinanceiro.services.SalarioService;

@RunWith(MockitoJUnitRunner.class)
public class SalarioServiceTest {

	@InjectMocks
	SalarioService service;
	
	
	@Mock
	ProfissionalFeignClient profissionalFeignClient;
	
	@Mock
	PedidoFeignClient pedidoFeignClient;
	
	Salario salarioJean;
	
	Profissional profissional;
	
	ResponseEntity<Optional<Profissional>> profissionalResponse;
	
	ResponseEntity<Double> comissaoResponse;
	
	String inicio;
	
	String fim;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		profissional = new Profissional("Jean", "(34)999708382", null, 0.0);
		
		salarioJean = new Salario("Jean", 0.0, 200.0);
		
		profissionalResponse = ResponseEntity.status(HttpStatus.OK).body(Optional.of(profissional));
		
		comissaoResponse = ResponseEntity.status(HttpStatus.OK).body(200.0);
		
		inicio = "2023-08-27";
		
		fim = "2023-08-28";
	}
	
	
	@Test
	public void deveBuscarSalarioDoProfissionalPorId() {
		Mockito.when(profissionalFeignClient.buscarPorId(1L)).thenReturn(profissionalResponse);
		Mockito.when(pedidoFeignClient.verificarComissaoPorPeriodo(1L, inicio , fim )).thenReturn(comissaoResponse);
		
		
               
       Salario salario = service.getSalario(1L, inicio, fim);
       
       Assertions.assertEquals(salario.getNomeProfissional(), salarioJean.getNomeProfissional());
       Assertions.assertEquals(salario.getSalarioFixo(), salarioJean.getSalarioFixo());
       Assertions.assertEquals(salario.getComissao(), salarioJean.getComissao());
       
       verify(profissionalFeignClient).buscarPorId(1L);
       verify(pedidoFeignClient).verificarComissaoPorPeriodo(1L, inicio, fim);
       verifyNoMoreInteractions(profissionalFeignClient, pedidoFeignClient);
       

       
	}
}
