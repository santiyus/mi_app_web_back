package com.miweb.jpa.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usuarios")
@Entity
public class EntiUsuario implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String nombre;

	@Column
	private String pwd;

	@Column
	private String rol;

	/**
	 * Constructor vacio
	 */
	public EntiUsuario() {
	}

	/**
	 * Constructor
	 * 
	 * @param id     long
	 * @param nobmre String
	 * @param pwd    String
	 * @param rol    String
	 */
	public EntiUsuario(long id, String nombre, String pwd, String rol) {
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

}
