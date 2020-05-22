package com.miweb.jpa.servicios;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miweb.jpa.conversor.Conversor;
import com.miweb.jpa.entidad.EntiCliente;
import com.miweb.jpa.repositorios.ClienteRepositorio;
import com.miweb.jpa.repositorios.UsuarioRepositorio;
import com.miweb.modelos.Cliente;
import com.miweb.modelos.UsuarioJWT;

@Service
public class ServiciosJpa {

	private static final Log logger = LogFactory.getLog(ServiciosJpa.class);

	@Autowired
	private Conversor conversor;
	@Autowired
	private UsuarioRepositorio repoUsuario;
	@Autowired
	private ClienteRepositorio repoCliente;

	/**
	 * Consulta el UsuarioJWT por el nombre
	 * 
	 * @param nombre String
	 * @return User
	 * @throws Exception
	 */
	public UsuarioJWT consultarUsuarioJWTPorNombre(String pNombre) throws Exception {
		logger.info("Consultando el usuarioJWT " + pNombre + " en bbdd.");
		return conversor.converEntiUsuarioToUsuario(repoUsuario.findByNombre(pNombre));
	}

	/**
	 * Consulta todos los clientes en la bbdd
	 * 
	 * @return List<Cliente>RuntimeException
	 * @throws Exception
	 */
	public List<Cliente> obtenerTodosClientes() throws Exception {
		logger.info("Consultando todos los clientes en bbdd.");
		return conversor.converListEntiClienteToListCliente(repoCliente.findAll());
	}

	/**
	 * Consulta el Cliente por el id
	 * 
	 * @param id long
	 * @return Cliente
	 * @throws Exception
	 */
	public Cliente consultarClientePorId(long pId) throws Exception {
		logger.info("Consultando el usuario con el id " + pId + " en bbdd.");
		return conversor.converEntiClienteToCliente(repoCliente.findById(pId));
	}

	/**
	 * Consulta Clientes por nombre
	 * 
	 * @param nombre String
	 * @return List<Cliente>
	 * @throws Exception
	 */
	public List<Cliente> consultarClientePorNombre(String pNombre) throws Exception {
		logger.info("Consultando el usuario con el nombre " + pNombre + " en bbdd.");
		return conversor.converListEntiClienteToListCliente(repoCliente.findByNombre(pNombre));
	}

	/**
	 * Consulta Clientes por apellido
	 * 
	 * @param apellido String
	 * @return List<Cliente>
	 * @throws Exception
	 */
	public List<Cliente> consultarClientePorApellidos(String pApellidos) throws Exception {
		logger.info("Consultando el usuario con los apellidos " + pApellidos + " en bbdd.");
		return conversor.converListEntiClienteToListCliente(repoCliente.findByApellidos(pApellidos));
	}

	/**
	 * Crea un Cliente en la bbdd
	 * 
	 * @param cliente Cliente
	 * @return boolean
	 * @throws Exception
	 */
	public boolean crearCliente(Cliente pCliente) throws Exception {
		logger.info("Creando cliente " + pCliente.getId());
		if (!existeCliente(pCliente))
			repoCliente.save(new EntiCliente(pCliente.getId(), pCliente.getNombre(), pCliente.getApellidos(),
					pCliente.getEmail(), pCliente.getN_telefono()));
		else
			throw new Exception("El cliente ya existe");

		return true;
	}

	/**
	 * Actualizar un Cliente en la bbdd
	 * 
	 * @param cliente Cliente
	 * @return boolean
	 * @throws Exception
	 */
	public boolean actualizarCliente(Cliente pCliente) throws Exception {
		logger.info("Actualizando cliente " + pCliente.getId());
		if (existeClienteId(pCliente.getId()))
			repoCliente.save(new EntiCliente(pCliente.getId(), pCliente.getNombre(), pCliente.getApellidos(),
					pCliente.getEmail(), pCliente.getN_telefono()));
		else
			throw new Exception("El cliente no existe");

		return true;
	}

	/**
	 * Borrar un Cliente de la bbdd por id
	 * 
	 * @param apellido String
	 * @return boolean
	 * @throws Exception
	 */
	public boolean borrarCliente(long pId) throws Exception {
		logger.info("Borrando cliente " + pId);
		repoCliente.deleteById(pId);
		return true;
	}

	/**
	 * Comprobar si un cliente ya existe pasandole un objeto cliente
	 * 
	 * @param cliente Cliente
	 * @return boolean
	 * @throws Exception
	 */
	public boolean existeCliente(Cliente pCliente) throws Exception {
		int count = repoCliente.comprobarExisteCliente(pCliente.getNombre(), pCliente.getApellidos(),
				pCliente.getEmail(), pCliente.getN_telefono());
		return count > 0;
	}

	/**
	 * Comprobar si un cliente ya existe pasandole el id
	 * 
	 * @param id int
	 * @return boolean
	 * @throws Exception
	 */
	public boolean existeClienteId(long pId) throws Exception {
		int count = repoCliente.comprobarExisteClienteId(pId);
		return count > 0;
	}

}
