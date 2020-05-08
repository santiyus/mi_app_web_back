package com.miweb.modelos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.miweb.jpa.entidad.EntiCliente;

public class Cliente {

	private long id;

	@NotNull
	@NotEmpty
	@Size(max = 45)
	private String nombre;

	@NotNull
	@NotEmpty
	@Size(max = 60)
	private String apellidos;

	@Email
	@NotNull
	@NotEmpty
	@Size(max = 60)
	private String email;

	@NotNull
	@NotEmpty
	@Size(max = 9)
	private String n_telefono;

	/**
	 * Constructor vacio
	 */
	public Cliente() {
	}

	/**
	 * Constructor para mapear la entidad EntiCliente
	 * 
	 * @param usuario EntiUsuario
	 */
	public Cliente(EntiCliente cliente) {
		super();
		this.id = cliente.getId();
		this.nombre = cliente.getNombre();
		this.apellidos = cliente.getApellidos();
		this.email = cliente.getEmail();
		this.n_telefono = cliente.getN_telefono();
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
	public Cliente(long id, String nombre, String primer_apellido, String segundo_apellido, String email,
			String n_telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = primer_apellido;
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

	public void setApellidos(String apellido) {
		this.apellidos = apellido;
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
