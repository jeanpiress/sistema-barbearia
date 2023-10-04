package com.jeanpiress.broauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.broauth.entities.Usuario;
import com.jeanpiress.broauth.feignClientes.UsuarioFeignClient;

@Service
public class UsuarioService {

	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioFeignClient usuarioFeignClient; 
	
	
	public Usuario buscarPorUserName(String user) {
		Usuario usuario = usuarioFeignClient.buscarPorUserName(user).getBody();
		
		if(usuario == null) {
			logger.error("User name não encontrado: " + user);
			throw new IllegalArgumentException("User name não encontrado");
		}
		
		logger.info("User name encontrado: " + user);
		return usuario;
	}
}
