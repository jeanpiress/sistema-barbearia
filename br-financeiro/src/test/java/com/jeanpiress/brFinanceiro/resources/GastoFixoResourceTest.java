package com.jeanpiress.brFinanceiro.resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Collections;
import java.util.Optional;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.entities.GastoFixo;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.services.GastoFixoService;

@RunWith(MockitoJUnitRunner.class)
public class GastoFixoResourceTest {

	@InjectMocks
	GastoFixoResource resource;

	@Mock
	GastoFixoService service;

	MockMvc mockMvc;
	
	GastoFixo gastoFixo;
	
	Credor imobiliaria;
	
	ObjectMapper objectMapper;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		imobiliaria = new Credor(1L, "imobiliaria", "aluguel");
			
		gastoFixo = new GastoFixo(1L, "Aluguel", 3870.0, imobiliaria, "aluguel", 20, true, PagamentoStatus.APAGAR);
		
		objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new JavaTimeModule());
		
		
	}

	@Test
	public void deveBuscarTodosOsGastosFixosEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(gastoFixo));

		mockMvc.perform(get("/gastosFixos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarGastosFixoPorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(gastoFixo));

		mockMvc.perform(get("/gastosFixos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarNovosGastossFixo() throws Exception {
		Mockito.when(service.cadastrar(gastoFixo)).thenReturn(gastoFixo);
		
		String gastoFixoJson = objectMapper.writeValueAsString(gastoFixo);
		
		mockMvc.perform(post("/gastosFixos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gastoFixoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(gastoFixo);
		verifyNoMoreInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoCadastrarSemInformarOGastoFixo() throws Exception {
				
		mockMvc.perform(post("/gastosFixos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmGastoFixoInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, gastoFixo)).thenReturn(gastoFixo);
		
		String gastoFixoJson = objectMapper.writeValueAsString(gastoFixo);
		
		mockMvc.perform(put("/gastosFixos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gastoFixoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, gastoFixo);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmGastoFixoSemPassarOId() throws Exception {
		String gastoFixoJson = objectMapper.writeValueAsString(gastoFixo);
		
		mockMvc.perform(put("/gastosFixos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gastoFixoJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmGastoFixoSemPassarOGastoFixoAlterado() throws Exception {
		
		
		mockMvc.perform(put("/gastosFixos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmGastoFixoInformandoId() throws Exception {
		
		mockMvc.perform(delete("/gastosFixos/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoDeletarUmGastoFixoSemPassarOId() throws Exception {
		
		mockMvc.perform(delete("/gastosFixos/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
}