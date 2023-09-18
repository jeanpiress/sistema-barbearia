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

import com.jeanpiress.brFinanceiro.entities.CaixaMensal;
import com.jeanpiress.brFinanceiro.services.CaixaMensalService;

@RunWith(MockitoJUnitRunner.class)
public class CaixaMensalResourceTest {

	@InjectMocks
	CaixaMensalResource resource;

	@Mock
	CaixaMensalService service;

	MockMvc mockMvc;
	
	CaixaMensal caixaMensal;
	
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
				
		caixaMensal = new CaixaMensal(1000.00, null, null, null, null, null);
		
			
	}

	
	@Test
	public void deveBuscarCaixaMensalPorMesEmJson() throws Exception {
		Mockito.when(service.buscarCaixaMensalPorMes(2023, 9)).thenReturn(caixaMensal);

		mockMvc.perform(get("/caixasMensais/ano/2023/mes/9")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarCaixaMensalPorMes(2023, 9);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarCaixaMensalDoMesAtualEmJson() throws Exception {
		Mockito.when(service.buscarCaixaMensalMesAtual()).thenReturn(caixaMensal);

		mockMvc.perform(get("/caixasMensais")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarCaixaMensalMesAtual();
		verifyNoMoreInteractions(service);
		

	}

}