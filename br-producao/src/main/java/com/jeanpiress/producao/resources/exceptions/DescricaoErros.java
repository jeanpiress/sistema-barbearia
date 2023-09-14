package com.jeanpiress.producao.resources.exceptions;

public class DescricaoErros {

	private String mensagemUsuario;
	private String mensagemDev;
	
		
	public DescricaoErros(String mensagemUsuario, String mensagemDev) {
		super();
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDev = mensagemDev;
	}
	
	public String getUsuario() {
		return mensagemUsuario;
	}
	public String getDev() {
		return mensagemDev;
	}
		
	
}
