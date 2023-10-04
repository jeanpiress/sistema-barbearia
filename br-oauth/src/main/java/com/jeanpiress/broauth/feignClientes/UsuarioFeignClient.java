package com.jeanpiress.broauth.feignClientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jeanpiress.broauth.entities.Usuario;

@Component
@FeignClient(name = "br-usuario", path = "/usuarios")
public interface UsuarioFeignClient {

	@GetMapping(value = "/user/{user}")
	ResponseEntity<Usuario> buscarPorUserName(@PathVariable String user);
		
		
}
