package com.jeanpiress.producao;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

import com.jeanpiress.producao.entities.CategoriaProduto;
import com.jeanpiress.producao.repository.CategoriaProdutoRepository;
import com.jeanpiress.producao.services.CategoriaProdutoService;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaProdutoServiceTest {

	@InjectMocks
	CategoriaProdutoService service;

	@Mock
	CategoriaProdutoRepository repository;
	
	CategoriaProduto categoriaProduto; 
	
	CategoriaProduto categoriaAlterada;
	
	List<CategoriaProduto> categorias = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		categoriaProduto = new CategoriaProduto(1L, "produtos", null);
		categoriaAlterada = new CategoriaProduto(null, "serviço", null);
				
		categorias.add(categoriaProduto);
		
		
		
	}

	@Test
	public void deveBuscarTodasCategorias() {
		Mockito.when(repository.findAll()).thenReturn(categorias);
		
        List<CategoriaProduto> categorias = service.buscar();
        
       Assertions.assertEquals(categorias, Collections.singletonList(categoriaProduto));
       
       verify(repository).findAll();
       verifyNoMoreInteractions(repository);

       
	}

	@Test
	public void debeBuscarCategoriaPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(categoriaProduto));
		
		Optional<CategoriaProduto> categoriaOptional = service.buscarPorId(categoriaProduto.getId());

		Assertions.assertEquals(categoriaOptional.get().getNome(), "produtos");
		verify(repository).findById(categoriaProduto.getId());
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveAdicionarUmaCategoria() {
		Mockito.when(repository.save(categoriaProduto)).thenReturn(categoriaProduto);
		
		CategoriaProduto categoriaSalva = service.cadastrar(categoriaProduto);

		Assertions.assertEquals(categoriaSalva, categoriaProduto);
		verify(repository).save(categoriaProduto);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmaCategoria() {
		Mockito.when(repository.save(categoriaProduto)).thenReturn(categoriaProduto);
		Mockito.when(repository.getReferenceById(1L)).thenReturn(categoriaProduto);
		
		CategoriaProduto categoriaProduto = service.alterar(1L, categoriaAlterada);

		Assertions.assertEquals(categoriaProduto.getId(), 1L);
		Assertions.assertEquals(categoriaProduto.getNome(), categoriaAlterada.getNome());
		
		verify(repository).save(categoriaProduto);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveDeletarUmaCategoria() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
}

}