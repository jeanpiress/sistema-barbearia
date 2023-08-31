package com.jeanpiress.brFinanceiro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeanpiress.brFinanceiro.entities.Boleto;
import com.jeanpiress.brFinanceiro.entities.CaixaMensal;
import com.jeanpiress.brFinanceiro.entities.GastoExtraordinario;
import com.jeanpiress.brFinanceiro.entities.GastoFixo;
import com.jeanpiress.brFinanceiro.entities.Salario;

public class CaixaMensalService {
	
	Double faturamento;
	List<Salario> salarios = new ArrayList<>();
	List<GastoExtraordinario> gastosExtraordinarios = new ArrayList<>();
	List<Boleto> boletos = new ArrayList<>();
	List<GastoFixo> gastosFixos = new ArrayList<>();

	@Autowired
	private RelatorioService pedidoService;
	
	@Autowired
	private GastoExtraordinarioService gastoExtraodinarioService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private GastoFixoService gastoFixosService;
	
	public CaixaMensal buscarCaixaMensalPorMes(int ano, int mes) {
		
		faturamento = pedidoService.faturamentoPorMes(ano, mes);
		salarios = pedidoService.salarioTodosProfissionaisMes(ano, mes);
		gastosExtraordinarios = gastoExtraodinarioService.buscarTodosGastosPorMes(ano, mes);
		boletos = boletoService.buscarTodosBoletosPorMes(ano, mes);
		gastosFixos = gastoFixosService.buscarTodosGastosFixosPorMes(ano, mes);
		
		CaixaMensal caixaMensal = new CaixaMensal(faturamento, salarios, boletos, gastosExtraordinarios, gastosFixos);
		
		return caixaMensal;
	}
	
	
}
