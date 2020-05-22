package com.miweb.modelos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Email {

	@NotNull
	@NotEmpty
	@Size(max = 30)
	private String nombre;

	@NotNull
	@NotEmpty
	@Size(max = 70)
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(max = 9)
	private String telefono;

	@NotNull
	@NotEmpty
	@Size(max = 30)
	private String asunto;

	@NotNull
	@NotEmpty
	@Size(max = 400)
	private String mensaje;
	
		
	
	public Email(String nombre, String email, String telefono, String asunto, String mensaje) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


}
