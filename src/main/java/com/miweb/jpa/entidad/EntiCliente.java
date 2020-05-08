package com.miweb.jpa.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "clientes")
public class EntiCliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String nombre;

	@Column
	private String apellidos;

	@Column
	private String email;

	@Column
	private String n_telefono;

	/**
	 * Constructor vacio
	 */
	public EntiCliente() {
	}

	/**
	 * Constructor
	 * 
	 * @param id         long
	 * @param nobmre     String
	 * @param apellidos  String
	 * @param email      String
	 * @param n_telefono String
	 */
	public EntiCliente(long id, String nombre, String apellidos, String email, String n_telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.n_telefono = n_telefono;
	}

	/**
	 * GETTERS Y SETTERS
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getN_telefono() {
		return n_telefono;
	}

	public void setN_telefono(String n_telefono) {
		this.n_telefono = n_telefono;
	}

}
