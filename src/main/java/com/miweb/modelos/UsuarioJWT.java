package com.miweb.modelos;

import com.miweb.jpa.entidad.EntiUsuario;

public class UsuarioJWT {

	private long id;

	private String nombre;

	private String pwd;

	private String rol;
	
	private String token;

	/**
	 * Constructor vacio
	 */
	public UsuarioJWT() {
	}

	/**
	 * Constructor para la entidad EntiUsuario
	 * 
	 * @param usuario EntiUsuario
	 */
	public UsuarioJWT(EntiUsuario user) {
		this.id = user.getId();
		this.nombre = user.getNombre();
		this.pwd = user.getPwd();
		this.rol = user.getRol();
	}

	/**
	 * Constructor
	 * 
	 * @param id     long
	 * @param nobmre String
	 * @param pwd    String
	 * @param rol    String
	 */
	public UsuarioJWT(long id, String nombre, String pwd, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pwd = pwd;
		this.rol = rol;
	}

	/**
	 * GETTERS Y SETTERS
	 */

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
