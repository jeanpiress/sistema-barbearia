package com.jeanpiress.broauth.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String userName;
	
	private String nome;
	private String telefone;
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	public Usuario() {
		
	}
		
	public Usuario(Long id, String userName, String nome, String telefone, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.nome = nome;
		this.telefone = telefone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}


	@Override
	public int hashCode() {
		return Objects.hash(telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(telefone, other.telefone);
	}




}

