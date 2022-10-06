package com.quiniela.api.utilities.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.quiniela.api.utilities.ISendEmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmailImpl implements ISendEmail {

	@Autowired
	private JavaMailSender sender;
	
	private String textMessage ="MUNDIAL QATAR 2022 ¡Pronostica en nuestra quiniela mundialista y gana increíbles premios en cada fase del mundial! UNETE A NUESTRA LIGA HACIENDO CLICK AL SIGUIENTE ENLACE miliga42.com";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailImpl.class);

	
	@Override
	public boolean sendEmailTo(String email, String subject)  {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
				
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(email);
			helper.setFrom("mundialquiniela2o22@gmail.com");
			helper.setText(textMessage);
			helper.setSubject(subject);
			sender.send(message);
			send = true;
			LOGGER.info("Mail enviado!");
		} catch (MessagingException e) {
			LOGGER.error("Hubo un error al enviar el mail: {}", e);
		}
		return send;
	}

}
