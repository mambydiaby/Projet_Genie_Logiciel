package com.flight_sharing.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	private static final String MAIL_SERVER = "smtp";
	private static final String SMTP_HOST_NAME = "mail.gmx.net";
	private static final int SMTP_HOST_PORT = 587;
	private static final String USER_NAME = "flight.sharing@gmx.com";
	private static final String PASSWORD = "gla2019+";

	public static void send(String to, List<String> cc, String subject, String body) {

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.user", USER_NAME);
		properties.put("mail.smtp.password", PASSWORD);
		properties.put("mail.smtp.port", SMTP_HOST_PORT);
		properties.put("mail.smtp.auth", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		try {
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(USER_NAME));
			InternetAddress toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);

			InternetAddress[] ccAddress = new InternetAddress[cc.size()];
			// To get the array of ccaddresses
			for (int i = 0; i < cc.size(); i++) {
				ccAddress[i] = new InternetAddress(cc.get(i));
			}

			// Set cc: header field of the header.
			for (int i = 0; i < ccAddress.length; i++) {
				message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
			}

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the date to actual message
			message.setSentDate(new Date());

			// Now set the actual message
			message.setContent(body, "text/html");
			Transport transport = session.getTransport(MAIL_SERVER);
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, USER_NAME, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent Message Successfully....");
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}

	public static void send(String to, String subject, String body) {

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.user", USER_NAME);
		properties.put("mail.smtp.password", PASSWORD);
		properties.put("mail.smtp.port", SMTP_HOST_PORT);
		properties.put("mail.smtp.auth", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		try {
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(USER_NAME));
			InternetAddress toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the date to actual message
			message.setSentDate(new Date());

			// Now set the actual message
			message.setContent(body, "text/html");
			Transport transport = session.getTransport(MAIL_SERVER);
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, USER_NAME, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent Message Successfully....");
		} catch (AddressException ae) {
			registerException(ae);
		} catch (MessagingException me) {
			registerException(me);
		}

	}

	private static void registerException(Exception e) {
		Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, e);
	}
}
