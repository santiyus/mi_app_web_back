package com.miweb.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miweb.jpa.servicios.ServiciosJpa;
import com.miweb.modelos.Cliente;

@PreAuthorize("authenticated") // para acceder Necesitan autenticarse
@RestController()
@RequestMapping("/crud")
//@CrossOrigin(origins = "http://localhost:4200")
public class CrudController {

	private final String URL_ALL_CLIENTES = "/all_clientes";
	private final String URL_CLIENTE_ID = "/cliente_id";
	private final String URL_CLIENTE_NOMBRE = "/cliente_nombre";
	private final String URL_CLIENTE_APELLIDOS = "/cliente_apellidos";
	private final String URL_CREAR_CLIENTE = "/crear_cliente";
	private final String URL_ACTULIZAR_CLIENTE = "/actualizar_cliente";
	private final String URL_BORRAR_CLIENTE = "/borrar_cliente";

	@Autowired
	private ServiciosJpa serviciosJpa;

	/**
	 * Metodo get para obtener una lista con todos los clientes
	 * 
	 * @return List<Cliente>
	 * @throws Exception
	 */
	@GetMapping(URL_ALL_CLIENTES)
	@PreAuthorize("hasRole('USER')") // solo puede acceder ROL USER
	public List<Cliente> allClientes() throws Exception {
		return serviciosJpa.obtenerTodosClientes();
	}

	/**
	 * Metodo get para obtener el cliente que contenga el id
	 * 
	 * @param id long
	 * @return Cliente
	 * @throws Exception
	 */
	@GetMapping(URL_CLIENTE_ID + "/{id}")
	@PreAuthorize("hasRole('USER')")
	public Cliente buscarClienteId(@NotNull @NotEmpty @PathVariable("id") long pId) throws Exception {
		return serviciosJpa.consultarClientePorId(pId);
	}

	/**
	 * Metodo get para obtener una lista con todos los clientes que tengan el mismos
	 * nombre
	 * 
	 * @param nombre String
	 * @return List<Cliente>
	 * @throws Exception
	 */
	@GetMapping(URL_CLIENTE_NOMBRE + "/{nombre}")
	@PreAuthorize("hasRole('USER')")
	public List<Cliente> buscarClienteNombre(@NotNull @NotEmpty @PathVariable("nombre") String pNombre)
			throws Exception {
		return serviciosJpa.consultarClientePorNombre(pNombre);
	}

	/**
	 * Metodo get para obtener una lista con todos los clientes que tenganlos mismos
	 * apellidos
	 * 
	 * @param apellidos String
	 * @return List<Cliente>
	 * @throws Exception
	 */
	@GetMapping(URL_CLIENTE_APELLIDOS + "/{apellidos}")
	@PreAuthorize("hasRole('USER')")
	public List<Cliente> buscarClienteApellidos(@NotNull @NotEmpty @PathVariable("apellidos") String pApellidos)
			throws Exception {
		return serviciosJpa.consultarClientePorApellidos(pApellidos);
	}

	/**
	 * Metodo put para crear un cliente ¡
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	@PutMapping(URL_CREAR_CLIENTE)
	@PreAuthorize("hasRole('USER')")
	public boolean crear_cliente(@Valid @RequestBody Cliente pCliente) throws Exception {
		return serviciosJpa.crearCliente(pCliente);
	}

	/**
	 * Metodo post para crear un cliente
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	@PostMapping(URL_ACTULIZAR_CLIENTE)
	@PreAuthorize("hasRole('USER')")
	public boolean actualizar_cliente(@Valid @RequestBody Cliente pCliente) throws Exception {
		return serviciosJpa.actualizarCliente(pCliente);
	}

	/**
	 * Metodo delete para borrar un cliente
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	@DeleteMapping(URL_BORRAR_CLIENTE + "/{id}")
	@PreAuthorize("hasRole('USER')")
	public boolean borrar_cliente(@NotNull @NotEmpty @PathVariable("id") long pId) throws Exception {
		return serviciosJpa.borrarCliente(pId);
	}

}
