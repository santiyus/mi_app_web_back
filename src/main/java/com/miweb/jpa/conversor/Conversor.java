package com.miweb.jpa.conversor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.miweb.jpa.entidad.EntiCliente;
import com.miweb.jpa.entidad.EntiUsuario;
import com.miweb.modelos.Cliente;
import com.miweb.modelos.UsuarioJWT;

@Component
public class Conversor {
	
	private static final Log logger = LogFactory.getLog(Conversor.class);

	/**
	 * Convierte una entidad EntiCliente a otra de tipo Cliente
	 * 
	 * @param pEntiClientes EntiCliente
	 * @return Cliente
	 */
	public Cliente converEntiClienteToCliente(EntiCliente entiCliente) {
		logger.info("Convertiendo entidad cliente a objeto cliente");
		return new Cliente(entiCliente);
	}	

	/**
	 * Convierte una lista de EntiCliente a otra de tipo Cliente
	 * 
	 * @param pEntiClientes List<EntiCliente>
	 * @return List <Cliente>
	 */
	public List<Cliente> converListEntiClienteToListCliente(List<EntiCliente> pEntiClientes) {
		logger.info("Convertiendo lista de entidades cliente a lista de cliente");
		List<Cliente> lClientes = new ArrayList<>();
		pEntiClientes.forEach(c -> lClientes.add(new Cliente(c)));
		return lClientes;
	}


	/**
	 * Convierte una lista de EntiUsuario a otra de tipo Usuario
	 * 
	 * @param pEntiUsuarios List<EntiUsuario>
	 * @return List<Usuario>
	 */
	public UsuarioJWT converEntiUsuarioToUsuario(EntiUsuario pEntiUsuarios) {
		logger.info("Convertiendo entidad usuario a objeto usuario");
		return new UsuarioJWT(pEntiUsuarios);
	}


	/**
	 * Convierte una lista de EntiUsuario a otra de tipo Usuario
	 * 
	 * @param pEntiUsuarios List<EntiUsuario>
	 * @return List<Usuario>
	 */
	public List<UsuarioJWT> converListEntiUsuarioToListUsuario(List<EntiUsuario> pEntiUsuarios) {
		logger.info("Convertiendo entidad usuario a objeto usuario");
		List<UsuarioJWT> lUsuarios = new ArrayList<>();
		pEntiUsuarios.forEach(u -> lUsuarios.add(new UsuarioJWT(u)));
		return lUsuarios;
	}

}
