package com.labutin.barman.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.LinkedBlockingQueue;

import com.mysql.jdbc.Driver;


public enum PoolConnection {
	POOL;
	private LinkedBlockingQueue<ProxyConnection> availableConnetion = new LinkedBlockingQueue<>();
	private LinkedBlockingQueue<ProxyConnection> unavailableConnetion = new LinkedBlockingQueue<>();
	private final String dbURL = "db.URL";
	private final String dbLogin = "db.Login";
	private final String dbPassword = "db.Password";
	//private final String dbProperties = "/Users/Mike/Documents/EpamJava/BarmanAssistant/res/DataBase.properties";
	private final String dbProperties ="resources/DataBase.properties";
	private String user;
	private String URL;
	private String password;
	private int poolSize = 10;

	public ProxyConnection getConnection() {
		if (!availableConnetion.isEmpty()) {
			ProxyConnection connection;
			try {
				connection = availableConnetion.take();
				unavailableConnetion.put(connection);
				return connection;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		throw new RuntimeException();
	}

	public void returnConnection(ProxyConnection connection) {
		unavailableConnetion.remove(connection);
		availableConnetion.add(connection);
	}

	public void initialization() {
		setConfing();
		System.out.println(URL);
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
		}
		try {
			DriverManager.registerDriver(DriverManager.getDriver(URL));
		} catch (SQLException e) {
			throw new RuntimeException("No drive", e);
		}

		for (int i = 0; i < poolSize; i++) {
			try {
				ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(URL, user, password));
				availableConnetion.add(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setConfing() {
		Properties property = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dbProperties);
		try {
			property.load(inputStream);
			URL = property.getProperty(dbURL);
			user = property.getProperty(dbLogin);
			password = property.getProperty(dbPassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}
}