package com.flight_sharing.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
	private final String sender = "flight.sharing@gmx.com";
	private final String password = "gla2019+";
	private String receiver;

	public Email(String receiver) {
		this.receiver = receiver;
	}

	public Email() {
		// TODO Auto-generated constructor stub
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void send(String subject, String content) throws MessagingException {
		final Properties properties = new Properties();

		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "mail.gmx.net");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.user", sender);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.starttls.enable", "true");

		Session mailSession = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("mail.smtp.user"),
						properties.getProperty("mail.smtp.password"));
			}
		});

		Message message = new MimeMessage(mailSession);
		InternetAddress addressTo = new InternetAddress(receiver);
		message.setRecipient(Message.RecipientType.TO, addressTo);
		message.setFrom(new InternetAddress(sender));
		message.setSubject(subject);
		message.setContent(content, "text/plain");
		Transport.send(message);
	}
}
