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

import com.jeanpiress.brFinanceiro.entity.Profissional;
import com.jeanpiress.brFinanceiro.entity.Salario;
import com.jeanpiress.brFinanceiro.feignclients.ProfissionalFeignClient;

@RunWith(MockitoJUnitRunner.class)
public class SalarioServiceTest {

	@InjectMocks
	SalarioService service;
	
	
	@Mock
	ProfissionalFeignClient profissionalFeignClient;
	
	Salario salarioJean;
	
	Profissional profissional;
	
	ResponseEntity<Optional<Profissional>> profissionalResponse;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		profissional = new Profissional("Jean", "(34)999708382", null, 0.0);
		
		salarioJean = new Salario("Jean", 0.0, null);
		
		profissionalResponse = ResponseEntity.status(HttpStatus.OK).body(Optional.of(profissional));
	}
	
	
	@Test
	public void deveBuscarSalarioDoProfissionalPorId() {
		Mockito.when(profissionalFeignClient.buscarPorId(1L)).thenReturn(profissionalResponse);
		
               
       Salario salario = service.getSalario(1L);
       
       Assertions.assertEquals(salario.getNomeProfissional(), salarioJean.getNomeProfissional());
       
       verify(profissionalFeignClient).buscarPorId(1L);
       verifyNoMoreInteractions(profissionalFeignClient);

       
	}
}
