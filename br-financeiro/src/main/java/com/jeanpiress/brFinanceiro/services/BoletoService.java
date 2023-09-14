package com.jeanpiress.brFinanceiro.services;

import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entities.Boleto;
import com.jeanpiress.brFinanceiro.repositories.BoletoRepository;

@Service
public class BoletoService {

	@Autowired
	BoletoRepository repository;

	

	public List<Boleto> buscar() {
		List<Boleto> boletos = repository.findAll();
		return boletos;
	}

	
	public Optional<Boleto> buscarPorId(Long id) {
		Optional<Boleto> boleto = repository.findById(id);
		return boleto;
	}



	public Boleto cadastrar(Boleto obj) {
		return repository.save(obj);
		
	}



	public Boleto alterar(Long id, Boleto obj) {
		Boleto boleto = repository.getReferenceById(id);
		update(boleto, obj); 
		return repository.save(boleto);
	}



	private void update(Boleto base, Boleto alterado) {
		base.setValor(alterado.getValor());
		base.setDataPagamento(alterado.getDataPagamento());
		
		
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public List<Boleto> buscarTodosBoletosPorMes(int ano, int mes) {
		List<Boleto> todosBoletos = buscar();
		List<Boleto> boletosMes = new ArrayList<>();
		
		for(Boleto boleto: todosBoletos) {
			YearMonth mesEAno = YearMonth.from(boleto.getDataPagamento().atZone(ZoneOffset.UTC));
			if(mesEAno.getYear() == ano && mesEAno.getMonthValue() == mes) {
				boletosMes.add(boleto);
			}
		}
		
			
		return boletosMes;
	}
	
	
	

}
