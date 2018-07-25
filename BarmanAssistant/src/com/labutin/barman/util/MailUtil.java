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

public class MailUtil implements Runnable {
	private static Logger logger = LogManager.getLogger();
	private String hostName;
	private int port;
	private String emailLogin;
	private String emailPassword;	
	private final static String textEmail = "thank you for registering on our website.";
	private final static String PATH_TO_PROPERTIES = "/resources/EmailConfig.properties";
	private String recipientEmail;
	private String recipientName;

	public MailUtil(String recipientEmail, String recipientName) {
		this.recipientEmail = recipientEmail;
		this.recipientName = recipientName;
		// TODO Auto-generated constructor stub
		Properties property = new Properties();
		try {
			InputStream inputStream = 
				    getClass().getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
			property.load(inputStream);
			hostName = property.getProperty("host");
			port = Integer.parseInt(property.getProperty("smtpPort"));
			emailLogin = property.getProperty("login");
			emailPassword = property.getProperty("password");
		} catch (IOException e) {
			logger.warn("File no found", e);
		}
	}

	@SuppressWarnings("deprecation")
	public void run() {
		// TODO Auto-generated method stub
          try {
        	  Email email = new SimpleEmail();
              email.setHostName(hostName);
              email.setSmtpPort(port);
              email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailPassword));
              email.setTLS(true);
              email.setFrom(emailLogin);
              email.setSubject("BarmanAssistant");
              email.setMsg(recipientName+"," + textEmail);
              email.addTo(recipientEmail);
			email.send();
			logger.info("Email to" + recipientName);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
