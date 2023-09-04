package com.jeanpiress.brFinanceiro.services;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entities.Cliente;
import com.jeanpiress.brFinanceiro.entities.Pedido;
import com.jeanpiress.brFinanceiro.entities.Profissional;
import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.feignclients.ClienteFeignClient;
import com.jeanpiress.brFinanceiro.feignclients.PedidoFeignClient;
import com.jeanpiress.brFinanceiro.feignclients.ProfissionalFeignClient;

@Service
public class RelatorioService {
	
	@Autowired
	private ProfissionalFeignClient profissionalFeignClient;
	
	@Autowired
	private PedidoFeignClient pedidoFeignClient;
	
	@Autowired
	private ClienteFeignClient clienteFeignClient;
	

	public Salario salarioFuncionarioPorPeriodo(Long profissionalId, String inicio, String fim) {
		Profissional profissional = profissionalFeignClient.buscarPorId(profissionalId).getBody().get();
		
		Double comissao = pedidoFeignClient.verificarComissaoPorPeriodo(profissionalId, inicio, fim).getBody();
		
		return new Salario(profissional.getNome(), profissional.getSalarioFixo(), comissao);
	}
	
	public Salario salarioFuncionarioMes(Long profissionalId, int ano, int mes) {
		Profissional profissional = profissionalFeignClient.buscarPorId(profissionalId).getBody().get();
				
		Double comissao = pedidoFeignClient.verificarComissaoProfissionalMes(profissionalId, ano, mes).getBody();
		
		return new Salario(profissional.getNome(), profissional.getSalarioFixo(), comissao);
		
	}
	
	public List<Salario> salarioTodosProfissionaisPeriodo(String inicio, String fim){
		Salario salario;
		List<Salario> salarios = new ArrayList<>();
		List<Profissional> profissionais = profissionalFeignClient.buscar().getBody();
		
		for(Profissional profissional: profissionais) {
			salario = salarioFuncionarioPorPeriodo(profissional.getId(), inicio, fim);
			salarios.add(salario);
		}
		
		
		return salarios;
	}
	
	public List<Salario> salarioTodosProfissionaisMes(int ano, int mes){
		Salario salario;
		List<Salario> salarios = new ArrayList<>();
		List<Profissional> profissionais = profissionalFeignClient.buscar().getBody();
		
		for(Profissional profissional: profissionais) {
			salario = salarioFuncionarioMes(profissional.getId(), ano, mes);
			salarios.add(salario);
		}
		
		
		return salarios;
	}
	
	public Double faturamentoPorMes(int ano, int mes) {
		List<Pedido> pedidos = pedidoFeignClient.getPedido().getBody();
		List<Pedido> pedidosSelecionados = new ArrayList<>();
		Double faturamento = 0.0;
		
		//Filtrando os Pedidos por mes e ano
		for (Pedido pedido: pedidos) {
			YearMonth mesEAno = YearMonth.from(pedido.getHorario().atZone(ZoneOffset.UTC));
			if(mesEAno.getYear() == ano && mesEAno.getMonthValue() == mes){
				pedidosSelecionados.add(pedido);
			}
		}
		
		for(Pedido pedidoFiltrado: pedidosSelecionados) {
			Double valorPedido = pedidoFiltrado.getValorTotal();
			faturamento += valorPedido;
		}
		
		
		return faturamento;
	}
	
	public List<Cliente> clientesNoDiaDeVoltar(String dataInicio, String dataFim){
		List<Cliente> clientes = clienteFeignClient.buscar().getBody();
		List<Cliente> clientesFiltrados = new ArrayList<>();
			
		Instant dataInicial = Instant.parse(dataInicio + "T00:00:00Z");
		Instant dataFinal = Instant.parse(dataFim + "T23:59:00Z");
		
		
		for(Cliente cliente: clientes) {
			if(cliente.getPrevisaoRetorno().isAfter(dataInicial) && cliente.getPrevisaoRetorno().isBefore(dataFinal) ) {
				clientesFiltrados.add(cliente);
			}
					
		}
		
		
		
		return clientesFiltrados;
	}
}
