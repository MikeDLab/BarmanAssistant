package com.labutin.barman.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailSender implements Runnable {
	private static Logger logger = LogManager.getLogger();
	private String hostName;
	private int port;
	private String emailLogin;
	private String emailPassword;
	private static final String EMAIL_TEXT = "thank you for registering on our website.";
	private static final String PATH_TO_PROPERTIES = "/resources/EmailConfig.properties";
	private String recipientEmail;
	private String recipientName;
	private static final String SENDER_LOGIN = "login";
	private static final String SENDER_PASSWORD = "password";
	private static final String HOST = "host";
	private static final String SMTP_PORT = "smtpPort";
	private static final String EMAIL_SUBJECT = "BarmanAssistant";

	public MailSender(String recipientEmail, String recipientName) {
		this.recipientEmail = recipientEmail;
		this.recipientName = recipientName;
		Properties property = new Properties();
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
			property.load(inputStream);
			hostName = property.getProperty(HOST);
			port = Integer.parseInt(property.getProperty(SMTP_PORT));
			emailLogin = property.getProperty(SENDER_LOGIN);
			emailPassword = property.getProperty(SENDER_PASSWORD);
		} catch (IOException e) {
			logger.warn("File Email confing not found", e);
		}
	}
	
	public void run() {
		try {
			Email email = new SimpleEmail();
			email.setHostName(hostName);
			email.setSmtpPort(port);
			email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailPassword));
			email.setFrom(emailLogin);
			email.setSubject(EMAIL_SUBJECT);
			email.setMsg(recipientName + "," + EMAIL_TEXT);
			email.addTo(recipientEmail);
			email.send();
			logger.info("Email to" + recipientName);
		} catch (EmailException e) {
			logger.warn("Email exception", e);
		}
	}

}
