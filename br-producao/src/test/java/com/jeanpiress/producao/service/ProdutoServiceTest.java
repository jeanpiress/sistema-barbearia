package com.jeanpiress.producao.service;

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

import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.repository.ProdutoRepository;
import com.jeanpiress.producao.services.ProdutoService;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

	@InjectMocks
	ProdutoService service;

	@Mock
	ProdutoRepository repository;

	Produto corte;

	Produto corteAlterado;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		corte = new Produto(1L, "Corte", 45.0, 0.0, 50.0, false, null, null);
		corteAlterado = new Produto(1L, "Corte maquina", 35.0, 0.0, 50.0, false, null, null);

	}

	@Test
	public void deveBuscarTodosProdutos() {
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(corte));

		List<Produto> produtos = service.buscar();

		Assertions.assertEquals(produtos, Collections.singletonList(corte));

		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void debeBuscarProdutoPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(corte));

		Optional<Produto> produtoOptional = service.buscarPorId(1L);

		Assertions.assertEquals(produtoOptional.get(), corte);
		verify(repository).findById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveAdicionarUmProduto() {
		Mockito.when(repository.save(corte)).thenReturn(corte);

		Produto produtoSalvo = service.cadastrar(corte);

		Assertions.assertEquals(produtoSalvo, corte);
		verify(repository).save(corte);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmProduto() {
		Mockito.when(repository.save(corte)).thenReturn(corte);
		Mockito.when(repository.getReferenceById(1L)).thenReturn(corte);

		Produto produto = service.alterar(1L, corteAlterado);

		Assertions.assertEquals(produto.getId(), 1L);
		Assertions.assertEquals(produto.getNome(), corteAlterado.getNome());
		Assertions.assertEquals(produto.getValor(), corteAlterado.getValor());

		verify(repository).save(corte);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}

	@Test
	public void deveDeletarUmProduto() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
	}

}