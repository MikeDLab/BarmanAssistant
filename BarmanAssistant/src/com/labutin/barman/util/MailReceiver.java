package com.labutin.barman.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class MailReceiver {
	private static Logger logger = LogManager.getLogger();
	private String hostName;
	private int port;
	private String emailLogin;
	private String emailPassword;
	private final String PATH_TO_PROPERTIES ="/Users/Mike/Documents/EpamJava/XMLParserWeb/resources/EmailConfig.properties";
	public MailReceiver() {
		// TODO Auto-generated constructor stub
		FileInputStream fis;
		Properties property = new Properties();
		try {
			fis = new FileInputStream(PATH_TO_PROPERTIES);
			property.load(fis);
			hostName = property.getProperty("host");
			port = Integer.parseInt(property.getProperty("smtpPort"));
			emailLogin = property.getProperty("login");
			emailPassword = property.getProperty("password");
		} catch (IOException e) {
			logger.warn("File no found",e);
		}
	}

	@SuppressWarnings("deprecation")
	public void sendEmail(String toWhom,String userName) {
		// TODO Auto-generated method stub
          try {
        	  Email email = new SimpleEmail();
              email.setHostName(hostName);
              email.setSmtpPort(port);
              email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailPassword));
              email.setTLS(true);
              email.setFrom(emailLogin);
              email.setSubject("Test");
              email.setMsg(userName);
              email.addTo(toWhom);
			email.send();
			logger.info("Email to" + userName);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
