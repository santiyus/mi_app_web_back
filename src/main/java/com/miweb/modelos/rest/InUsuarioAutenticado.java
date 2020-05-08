package com.miweb.modelos.rest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InUsuarioAutenticado {
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String user;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String pass;

	/**
	 * Constructor vacio
	 */
	public InUsuarioAutenticado() {
		super();
	}

	/**
	 * Constructor para la entidad EntiCliente
	 * 
	 * @param userName String
	 */
	public InUsuarioAutenticado(String pUserName, String pPassword) {
		super();
		this.user = pUserName;
		this.pass = pPassword;
	}

	/**
	 * GETTERS Y SETTERS
	 */
	public String getUser() {
		return user;
	}

	public void setUser(String userName) {
		this.user = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String password) {
		this.pass = password;
	}

}
