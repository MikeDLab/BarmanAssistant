package com.labutin.barman.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.mysql.jdbc.Driver;

public enum PoolConnection {
	POOL;
	private LinkedBlockingQueue<ProxyConnection> availableConnetion = new LinkedBlockingQueue<>();
	private LinkedBlockingQueue<ProxyConnection> unavailableConnetion = new LinkedBlockingQueue<>();
	private final String dbURL = "db.URL";
	private final String dbLogin = "db.Login";
	private final String dbPassword = "db.Password";
	private final String dbProperties = "resources/DataBase.properties";
	private String user;
	private String URL;
	private String password;
	private int poolSize = 10;
	private AtomicBoolean isCreated = new AtomicBoolean(false);

	public ProxyConnection getConnection() {

		if (!availableConnetion.isEmpty()) {
			ProxyConnection connection;
			try {
				if (availableConnetion.size() > 20) {
					destroyFree();
				}
				connection = availableConnetion.take();
				unavailableConnetion.put(connection);
				System.out.println("Connctions: " + availableConnetion.size());
				System.out.println("Unvaible: " + unavailableConnetion.size());
				return connection;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		throw new RuntimeException();
	}

	private void destroyFree() {
		while (availableConnetion.size() > 15) {
			try {
				availableConnetion.poll().closeInPool();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void returnConnection(ProxyConnection connection) {

		try {
			if (!connection.getAutoCommit()) {
				connection.setAutoCommit(true);
			}
			unavailableConnetion.remove(connection);
			availableConnetion.put(connection);
		} catch (SQLException | InterruptedException e) {
			System.out.println("Plobem when returned");
		}
	}

	public void initialization() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		if (!isCreated.get()) {
			setConfing();
			try {
				DriverManager.registerDriver(new Driver());
			} catch (SQLException e) {
				throw new NoJDBCDriverException(e);
			}
			try {
				DriverManager.registerDriver(DriverManager.getDriver(URL));
			} catch (SQLException e) {
				throw new NoJDBCDriverException(e);
			}

			for (int i = 0; i < poolSize; i++) {
				try {
					ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(URL, user, password));
					availableConnetion.add(connection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new NoJDBCDriverException(e);
				}
			}
			isCreated.set(true);
		}
	}

	private void setConfing() throws NoJDBCPropertiesFileException {
		Properties property = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dbProperties);
		try {
			property.load(inputStream);
			URL = property.getProperty(dbURL);
			user = property.getProperty(dbLogin);
			password = property.getProperty(dbPassword);
		} catch (IOException e) {
			throw new NoJDBCPropertiesFileException();
		}

	}
}
