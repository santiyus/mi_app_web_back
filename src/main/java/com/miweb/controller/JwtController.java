package com.miweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.miweb.modelos.rest.InUsuarioAutenticado;
import com.miweb.seguridad.servicios.ServiciosJWT;


@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class JwtController {
	public final String URL_AUTENTICARSE = "/autenticarse";
	
	@Autowired
	private ServiciosJWT serviciosJwt;

	/**
	 * Mediante los parametros recibidos crea y devuelve el token
	 * 
	 * @param pName String
	 * @param pPwd String
	 *
	 * @return String
	 * @throws Exception 
	 */
	
	@PostMapping(URL_AUTENTICARSE)
	public String login(@Valid @RequestBody InUsuarioAutenticado pUser) throws Exception {
		return serviciosJwt.autenticarse(pUser);
		
	}

}
