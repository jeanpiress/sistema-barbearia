package com.jeanpiress.producao.services;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.producao.entities.Pedido;
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.entities.enums.FormaPagamento;
import com.jeanpiress.producao.entities.enums.PagamentoStatus;
import com.jeanpiress.producao.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repository;

	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ClienteService clienteService;

	public List<Pedido> buscar() {
		List<Pedido> pedidos = repository.findAll();
		return pedidos;
	}

	public Optional<Pedido> buscarPorId(Long id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido;
	}

	public Pedido cadastrar(Pedido obj) {
		return repository.save(obj);

	}

	public Pedido alterar(Long id, Pedido obj) {
		Pedido pedido = repository.getReferenceById(id);
		update(pedido, obj);
		return repository.save(pedido);
	}

	private void update(Pedido base, Pedido alterado) {
		base.setCliente(alterado.getCliente());
		base.setDescricao(alterado.getDescricao());
		base.setHorario(alterado.getHorario());
		base.setProfissional(alterado.getProfissional());

	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}

	public Pedido adicionarProduto(Long id, Long produtoId) {
		Pedido pedido = buscarPorId(id).get();
		pedido.setPagamentoStatus(PagamentoStatus.APAGAR);
		pedido.setFormaPagamento(FormaPagamento.ESPERANDO);
		Produto produto = produtoService.buscarPorId(produtoId).get();
		List<Produto> produtos = pedido.getProdutos();
		produtos.add(produto);
		pedido.setProdutos(produtos);
		atulaizarValor(pedido);
		Double comissao = comissaoPagaPorPedido(pedido);
		pedido.setComissao(comissao);
		
		return repository.save(pedido);
	}

	public Pedido removerProduto(Long id, Long produtoId) {
		Pedido pedido = buscarPorId(id).get();
		Produto produto = produtoService.buscarPorId(produtoId).get();
		List<Produto> produtos = pedido.getProdutos();
		produtos.remove(produto);
		pedido.setProdutos(produtos);
		atulaizarValor(pedido);

		return repository.save(pedido);
	}

	public Pedido atulaizarValor(Pedido pedido) {
		List<Produto> produtos = pedido.getProdutos();
		Double total = 0.0;
		for (Produto produto : produtos) {
			total += produto.getValor();
		}
		pedido.setValorTotal(total);

		return pedido;
	}

	public Double comissaoPagaPorId(Long id) {
		Pedido pedido = buscarPorId(id).get();
		List<Produto> produtos = pedido.getProdutos();
		Double comissao = 0.0;
		for (Produto produto : produtos) {
			Double valor = produto.getValor();
			Double porcentagemComissao = produto.getComissao();
			Double comissaoProduto = valor * porcentagemComissao / 100;
			comissao += comissaoProduto;
		}

		return comissao;
	}

	public Double comissaoPagaPorPedido(Pedido pedido) {
		List<Produto> produtos = pedido.getProdutos();
		Double comissao = 0.0;
		for (Produto produto : produtos) {
			Double valor = produto.getValor();
			Double porcentagemComissao = produto.getComissao();
			Double comissaoProduto = valor * porcentagemComissao / 100;
			comissao += comissaoProduto;
		}

		return comissao;
	}
	
	

	public Double comissaoPagaPorPeriodo(Long profissionalId, String inicio, String fim) {

		Instant dataInicio = Instant.parse(inicio + "T00:00:00Z");
		Instant dataFim = Instant.parse(fim + "T23:59:59Z");
		
		List<Pedido> todosPedidos = buscar();
		List<Pedido> pedidosSelecionados = new ArrayList<>();
		Double comissao = 0.0;

		// Buscando todos os pedidos que estão pagos e dentro da data selecionada
		for (Pedido pedidoGeral : todosPedidos) {
			if (pedidoGeral.getProfissional().getId() == profissionalId 
					&& pedidoGeral.getHorario().isAfter(dataInicio)
					&& pedidoGeral.getHorario().isBefore(dataFim)
					&& pedidoGeral.getPagamentoStatus() == PagamentoStatus.PAGO) {
				
				pedidosSelecionados.add(pedidoGeral);
			}

		}
		
		for (Pedido pedidoTratado : pedidosSelecionados) {
			comissao += comissaoPagaPorPedido(pedidoTratado);
		}

		return comissao;
	}
	
	
	public Double comissaoPagaProfissionalMes(Long profissionalId, int ano, int mes) {
				
		List<Pedido> todosPedidos = buscar();
		List<Pedido> pedidosSelecionados = new ArrayList<>();
		Double comissao = 0.0;

		// Buscando todos os pedidos que estão pagos e dentro do mes e ano selecionada
		for (Pedido pedidoGeral : todosPedidos) {
			YearMonth mesEAno = YearMonth.from(pedidoGeral.getMomentoPagamento().atZone(ZoneOffset.UTC));
			if (pedidoGeral.getProfissional().getId() == profissionalId 
					&& mesEAno.getYear() == ano
					&& mesEAno.getMonthValue() == mes
					&& pedidoGeral.getPagamentoStatus() == PagamentoStatus.PAGO) {
				
				pedidosSelecionados.add(pedidoGeral);
			}

		}
		
		for (Pedido pedidoTratado : pedidosSelecionados) {
			comissao += comissaoPagaPorPedido(pedidoTratado);
		}

		return comissao;
	}
	
	public Pedido pagamentoPedido(Long pedidoId, int formaPagamento) {
		Pedido pedido = repository.getReferenceById(pedidoId);
		Instant previsaoRetorno = clienteService.atualizarPrevisaoRetorno(pedido.getCliente().getId());
		pedido.setFormaPagamento(FormaPagamento.valueOf(formaPagamento));
		pedido.setPagamentoStatus(PagamentoStatus.PAGO);
		pedido.getCliente().setUltimaVisita(Instant.now().truncatedTo(ChronoUnit.SECONDS));
		pedido.getCliente().setPrevisaoRetorno(previsaoRetorno);
		pedido.setMomentoPagamento(Instant.now());
		
		
		return repository.save(pedido);
		
	}

}
