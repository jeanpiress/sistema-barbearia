package com.jeanpiress.brFinanceiro.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entities.GastoFixo;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.repositories.GastoFixoRepository;

@Service
public class GastoFixoService {

	@Autowired
	GastoFixoRepository repository;

	public List<GastoFixo> buscar() {
		List<GastoFixo> gastoFixos = repository.findAll();
		return gastoFixos;
	}

	public Optional<GastoFixo> buscarPorId(Long id) {
		Optional<GastoFixo> gastoFixo = repository.findById(id);
		return gastoFixo;
	}

	public GastoFixo cadastrar(GastoFixo obj) {
		obj.setAtivo(true);
		return repository.save(obj);

	}

	public GastoFixo alterar(Long id, GastoFixo obj) {
		GastoFixo gastoFixo = repository.getReferenceById(id);
		update(gastoFixo, obj);
		return repository.save(gastoFixo);
	}

	private void update(GastoFixo base, GastoFixo alterado) {
		base.setNome(alterado.getNome());
		base.setValor(alterado.getValor());
		base.setDiaVencimento(alterado.getDiaVencimento());
		base.setMotivo(alterado.getMotivo());
		base.setDiaVencimentoRecorrente(alterado.getDiaVencimentoRecorrente());

	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}

	public List<GastoFixo> buscarTodosGastosFixosAtivosMes(int ano, int mes) {
		List<GastoFixo> todosGastosFixos = buscar();
		List<GastoFixo> gastosFixosMes = new ArrayList<>();

		for (GastoFixo gastoFixo : todosGastosFixos) {
			YearMonth mesEAno = YearMonth.from(gastoFixo.getDiaVencimento().atZone(ZoneOffset.UTC));
			if (mesEAno.getYear() == ano && mesEAno.getMonthValue() == mes && gastoFixo.isAtivo() == true) {
				gastosFixosMes.add(gastoFixo);
			}
		}

		return gastosFixosMes;
	}

	public void gerarGastoFixoMes() {
		List<GastoFixo> gastosFixos = buscar();
		List<GastoFixo> gastosRecentes = new ArrayList<>();
		GastoFixo novoGastoFixo;
		
		Instant agora = Instant.now();
		
		//Definindo primeiro dia do mes atual
		LocalDateTime agoraLDT = agora.atZone(ZoneOffset.UTC).toLocalDateTime();
		LocalDateTime primeiroDiaMesLDT = agoraLDT.with(ChronoField.DAY_OF_MONTH, 1);
		Instant inicioMesAtual = primeiroDiaMesLDT.atZone(ZoneOffset.UTC).toInstant();
		
				
		//Definindo Pirmeiro dia do mes passado
		LocalDateTime primeiroDiaMesPassadoLDT = primeiroDiaMesLDT.minusMonths(1);
		Instant primeiroDiaMesPassado = primeiroDiaMesPassadoLDT.atZone(ZoneOffset.UTC).toInstant();
		
		
		//desbrindo se o mes já foi atulaizado, caso atualizado não fazer nada
		for(GastoFixo gastoFixo: gastosFixos) {
			if(gastoFixo.getDiaVencimento().isAfter(inicioMesAtual)) {
				gastosRecentes.add(gastoFixo);
			}
		}
		
		//caso não tenha sido atualizado ira atualizar daqui pra baixo
		if(gastosRecentes.isEmpty()) {
			for(GastoFixo gastoFixo: gastosFixos) {
				if(gastoFixo.getDiaVencimento().isAfter(primeiroDiaMesPassado) && gastoFixo.isAtivo() == true) {
					gastosRecentes.add(gastoFixo);
				}
			}
		
		for (GastoFixo gastoFixo : gastosRecentes) {
			int dataRecorrente = gastoFixo.getDiaVencimentoRecorrente();
			Instant vencimentoAtualizado = inicioMesAtual;
			LocalDateTime vencimentoLDT = vencimentoAtualizado.atZone(ZoneOffset.UTC).toLocalDateTime();
			vencimentoLDT = vencimentoLDT.with(ChronoField.DAY_OF_MONTH, dataRecorrente);
			vencimentoAtualizado = vencimentoLDT.atZone(ZoneOffset.UTC).toInstant();
			
			novoGastoFixo = new GastoFixo(null, gastoFixo.getNome(), gastoFixo.getValor(), gastoFixo.getMotivo(),
					vencimentoAtualizado, true, PagamentoStatus.APAGAR, gastoFixo.getDiaVencimentoRecorrente());
			
			repository.save(novoGastoFixo);
		}
	}

	}
	
	public void desativarGastoFixo(Long id) {
		GastoFixo gastoFixo = repository.getReferenceById(id);
		gastoFixo.setAtivo(false);
		repository.save(gastoFixo);
	}
}
