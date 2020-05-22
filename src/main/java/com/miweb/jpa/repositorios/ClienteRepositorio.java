package com.miweb.jpa.repositorios;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miweb.jpa.entidad.EntiCliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<EntiCliente, Serializable>{
	/**
	 * Consulta un cliente mediante su id
	 * @param pNombre String
	 * @return  EntiCliente
	 */
	public abstract EntiCliente findById(long id);	

	/**
	 * Consulta un cliente mediante su nombre
	 * @param pNombre String
	 * @return  EntiCliente
	 */
	public abstract List<EntiCliente> findByNombre(String nombre);

	/**
	 * Consulta un cliente mediante su apellidos
	 * @param pNombre String
	 * @return  EntiCliente
	 */
	public abstract List<EntiCliente> findByApellidos(String apellidos);
	

	/**
	 * Consulta si un cliente ya existe. Si es diferente de 0 es que si existe
	 * @param pNombre String
	 * @return  int 
	 */
	@Query(value = "SELECT count(*) FROM Clientes c WHERE c.id = :id", 
			  nativeQuery = true)
	public abstract int comprobarExisteClienteId(@Param("id") long id);

	/**
	 * Consulta si un cliente ya existe. Si es diferente de 0 es que si existe
	 * @param pNombre String
	 * @return  int 
	 */
	@Query(value = "SELECT count(*) FROM Clientes c WHERE c.nombre = :nombre "
			+ "and c.apellidos = :apellidos and c.email = :email and c.n_telefono = :n_telefono", 
			  nativeQuery = true)
	public abstract int comprobarExisteCliente(@Param("nombre") String nombre, 
			@Param("apellidos") String apellidos, @Param("email") String email, 
			@Param("n_telefono") String n_telefono);

}
