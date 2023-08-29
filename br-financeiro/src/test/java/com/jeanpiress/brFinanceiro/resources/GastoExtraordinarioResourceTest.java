package com.jeanpiress.brFinanceiro.resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.Instant;
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
import com.jeanpiress.brFinanceiro.entities.GastoExtraordinario;
import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.services.GastoExtraordinarioService;

@RunWith(MockitoJUnitRunner.class)
public class GastoExtraordinarioResourceTest {

	@InjectMocks
	GastoExtraordinarioResource resource;

	@Mock
	GastoExtraordinarioService service;

	MockMvc mockMvc;
	
	GastoExtraordinario gastoExtraordinario;
	
	Credor loreal;
	
	Instant dataPagamento;
	
	ObjectMapper objectMapper;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		loreal = new Credor(1L, "loreal", "produtos");
		dataPagamento = Instant.parse("2023-08-28T00:00:00Z");
		
		gastoExtraordinario = new GastoExtraordinario(1L, loreal, 350.0, dataPagamento, PagamentoStatus.PAGO);
		
		objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new JavaTimeModule());
		
		
	}

	@Test
	public void deveBuscarTodosOsGastosExtraordinariosEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(gastoExtraordinario));

		mockMvc.perform(get("/gastosExtraordinarios")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarGastoExtraordinarioPorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(gastoExtraordinario));

		mockMvc.perform(get("/gastosExtraordinarios/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarNovoGastoExtraordinario() throws Exception {
		Mockito.when(service.cadastrar(gastoExtraordinario)).thenReturn(gastoExtraordinario);
		
		String gastoExtraordinarioJson = objectMapper.writeValueAsString(gastoExtraordinario);
		
		mockMvc.perform(post("/gastosExtraordinarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gastoExtraordinarioJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(gastoExtraordinario);
		verifyNoMoreInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoCadastrarSemInformarOGastoExtraordinario() throws Exception {
				
		mockMvc.perform(post("/gastosExtraordinarios")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmGastoExtraordinarioInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, gastoExtraordinario)).thenReturn(gastoExtraordinario);
		
		String gastoExtraordinarioJson = objectMapper.writeValueAsString(gastoExtraordinario);
		
		mockMvc.perform(put("/gastosExtraordinarios/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gastoExtraordinarioJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, gastoExtraordinario);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmGastoExtraordinarioSemPassarOId() throws Exception {
		String gastoExtraordinarioJson = objectMapper.writeValueAsString(gastoExtraordinario);
		
		mockMvc.perform(put("/gastosExtraordinarios/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gastoExtraordinarioJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmGastoExtraordinarioSemPassarOGastoExtraordinarioAlterado() throws Exception {
		
		
		mockMvc.perform(put("/gastosExtraordinarios/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmGastoExtraordinarioInformandoId() throws Exception {
		
		mockMvc.perform(delete("/gastosExtraordinarios/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoDeletarUmGastoExtraordinarioSemPassarOId() throws Exception {
		
		mockMvc.perform(delete("/gastosExtraordinarios/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
}