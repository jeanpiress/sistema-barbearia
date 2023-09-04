package com.jeanpiress.brFinanceiro.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.brFinanceiro.entities.CaixaMensal;
import com.jeanpiress.brFinanceiro.services.CaixaMensalService;

@RestController
@RequestMapping(value = "/caixasMensais")
public class CaixaMensalResource {

	@Autowired
	CaixaMensalService service;
	
	
	@GetMapping(value = "/ano/{ano}/mes/{mes}")
	ResponseEntity<CaixaMensal> CaixaMensalPorMes(@PathVariable int ano, @PathVariable int mes){
		CaixaMensal caixaMensal = service.buscarCaixaMensalPorMes(ano, mes);
		
		return ResponseEntity.ok(caixaMensal);
	}
}
