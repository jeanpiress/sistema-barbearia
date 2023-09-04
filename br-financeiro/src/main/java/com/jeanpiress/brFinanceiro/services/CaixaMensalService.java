package com.jeanpiress.brFinanceiro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeanpiress.brFinanceiro.entities.Boleto;
import com.jeanpiress.brFinanceiro.entities.CaixaMensal;
import com.jeanpiress.brFinanceiro.entities.GastoExtraordinario;
import com.jeanpiress.brFinanceiro.entities.GastoFixo;
import com.jeanpiress.brFinanceiro.entities.Salario;

public class CaixaMensalService {

	@Autowired
	private RelatorioService relatorioService;

	@Autowired
	private GastoExtraordinarioService gastoExtraodinarioService;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private GastoFixoService gastoFixosService;

	public CaixaMensal buscarCaixaMensalPorMes(int ano, int mes) {
		Double faturamento = relatorioService.faturamentoPorMes(ano, mes);
		List<Salario> salarios = relatorioService.salarioTodosProfissionaisMes(ano, mes);
		List<GastoExtraordinario> gastosExtraordinarios = gastoExtraodinarioService.buscarTodosGastosPorMes(ano, mes);
		List<Boleto> boletos = boletoService.buscarTodosBoletosPorMes(ano, mes);
		List<GastoFixo> gastosFixos = gastoFixosService.buscarTodosGastosFixosPorMes(ano, mes);
		Double lucro = calculoLucro(faturamento, salarios, gastosExtraordinarios, boletos, gastosFixos);

		CaixaMensal caixaMensal = new CaixaMensal(faturamento, salarios, boletos, gastosExtraordinarios, gastosFixos,
				lucro);

		return caixaMensal;
	}

	public Double calculoLucro(Double faturamento, List<Salario> salarios,
			List<GastoExtraordinario> gastosExtraordinarios, List<Boleto> boletos, List<GastoFixo> gastosFixos) {
		Double lucro = faturamento;

		for (Salario salario : salarios) {
			lucro -= salario.getSalarioFixo();
			lucro -= salario.getComissao();
		}

		for(GastoExtraordinario gastoExtra: gastosExtraordinarios) {
			lucro -= gastoExtra.getValor();
			
		}
		
		for(Boleto boleto: boletos) {
			lucro -= boleto.getValor();
		}
		
		for(GastoFixo gastoFixo: gastosFixos) {
			lucro -= gastoFixo.getValor();
		}
		
		return lucro;
	}

}
