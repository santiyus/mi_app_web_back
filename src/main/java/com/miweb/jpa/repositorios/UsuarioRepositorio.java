package com.miweb.jpa.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miweb.jpa.entidad.EntiUsuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<EntiUsuario, Serializable>{
	/**
	 * Consulta un Usuario mediante su nombre
	 * @param pNombre String
	 * @return  EntiUsuario
	 */
	public abstract EntiUsuario findByNombre(String nombre);
	
	/**
	 * Consulta un Usuario mediante su nombre
	 * @param pNombre String
	 * @return  EntiUsuario
	 */
	public abstract EntiUsuario findByRol(String rol);
	

}
