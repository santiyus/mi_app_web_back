package com.miweb.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.miweb.modelos.Email;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String mailFrom;

	@Value("${mailTo}")
	private String mailTo;

	private final String SALTO_LINEA = "\n";

	private static final Log logger = LogFactory.getLog(EmailService.class);

	/**
	 * Transforma el usuario en user
	 * 
	 * @param pUsername String
	 * @return User
	 * @exception UsernameNotFoundException
	 */
	public boolean sendMail(Email pEmail) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		StringBuilder sb = new StringBuilder();
		sb.append("Nombre -->" + pEmail.getNombre() + SALTO_LINEA);
		sb.append("Email -->" + pEmail.getEmail() + SALTO_LINEA);
		sb.append("Telefono -->" + pEmail.getEmail() + SALTO_LINEA);
		sb.append("Mensaje -->"+ SALTO_LINEA + pEmail.getMensaje() + SALTO_LINEA);

		mailMessage.setFrom(mailFrom);
		mailMessage.setTo(mailTo);
		mailMessage.setSubject(pEmail.getAsunto());
		mailMessage.setText(sb.toString());
		javaMailSender.send(mailMessage);
		
		return true;

	}

}
