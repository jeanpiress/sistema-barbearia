package com.jeanpiress.brFinanceiro.resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.services.RelatorioService;

@RunWith(MockitoJUnitRunner.class)
public class RelatorioResourceTest {

	@InjectMocks
	RelatorioResource resource;

	@Mock
	RelatorioService service;

	MockMvc mockMvc;
	
	Salario salario;
	
	String inicio;
	
	String fim;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
				
		salario = new Salario(null, null, null);
		
		inicio = "2023-09-01";
		
		fim = "2023-09-30";
			
	}

	
	@Test
	public void deveBuscarSalarioPorMesEmJson() throws Exception {
		Mockito.when(service.salarioFuncionarioMes(1L, 2023, 9)).thenReturn(salario);

		mockMvc.perform(get("/relatorios/profissional/1/ano/2023/mes/9")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).salarioFuncionarioMes(1L, 2023, 9);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarSalarioPorPeriodoEmJson() throws Exception {
		Mockito.when(service.salarioFuncionarioPorPeriodo(1L, inicio, fim)).thenReturn(salario);

		mockMvc.perform(get("/relatorios/profissional/1/inicio/2023-09-01/fim/2023-09-30")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).salarioFuncionarioPorPeriodo(1L, inicio, fim);
		verifyNoMoreInteractions(service);
		

	}

}