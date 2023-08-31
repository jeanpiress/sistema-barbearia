package com.jeanpiress.brFinanceiro.services;

import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entities.GastoFixo;
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
		return repository.save(obj);
		
	}



	public GastoFixo alterar(Long id, GastoFixo obj) {
		GastoFixo gastoFixo = repository.getReferenceById(id);
		update(gastoFixo, obj); 
		return repository.save(gastoFixo);
	}



	private void update(GastoFixo base, GastoFixo alterado) {
		base.setNome(alterado.getNome());
		base.setCredor(alterado.getCredor());
		base.setValor(alterado.getValor());
		base.setDiaVencimento(alterado.getDiaVencimento());
		base.setMotivo(alterado.getMotivo());
				
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public List<GastoFixo> buscarTodosGastosFixosPorMes(int ano, int mes) {
		List<GastoFixo> todosGastosFixos = buscar();
		List<GastoFixo> gastosFixosMes = new ArrayList<>();
		
		for(GastoFixo gastoFixo: todosGastosFixos) {
			YearMonth mesEAno = YearMonth.from(gastoFixo.getDiaVencimento().atZone(ZoneOffset.UTC));
			if(mesEAno.getYear() == ano && mesEAno.getMonthValue() == mes) {
				gastosFixosMes.add(gastoFixo);
			}
		}
		
			
		return gastosFixosMes;
	}
	
	
	
	

}
