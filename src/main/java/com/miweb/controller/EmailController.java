package com.miweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.miweb.modelos.Email;
import com.miweb.modelos.rest.InUsuarioAutenticado;
import com.miweb.seguridad.servicios.ServiciosJWT;
import com.miweb.services.EmailService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class EmailController {
	public final String URL_EMAIL = "/email";

	@Autowired
	private EmailService emailService;

	/**
	 * Mediante los parametros recibidos envia un correo
	 * 
	 * @param pMail Email
	 * 
	 * @return Boolean
	 * @throws Exception 
	 */
	
	@PostMapping(URL_EMAIL)
	public boolean login(@Valid @RequestBody Email pMail) throws Exception {
		return emailService.sendMail(pMail);
	}

}
