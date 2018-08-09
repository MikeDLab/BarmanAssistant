package com.labutin.barman.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.mysql.jdbc.Driver;

public enum PoolConnection {
	POOL;
	private LinkedBlockingQueue<ProxyConnection> availableConnetion = new LinkedBlockingQueue<>();
	private ArrayDeque<ProxyConnection> unavailableConnetion = new ArrayDeque<>();
	private static final String DATABASE_URL = "db.URL";
	private static final String DATABASE_USER_LOGIN = "db.Login";
	private static final String DATABASE_USER_PASSWORD = "db.Password";
	private static final String DATABASE_PROPERTIES = "resources/DataBase.properties";
	private String user;
	private String URL;
	private String password;
	private static final int POOL_SIZE = 32;
	private static Logger logger = LogManager.getLogger();
	private AtomicBoolean isCreated = new AtomicBoolean(false);

	public ProxyConnection getConnection() {
		ProxyConnection connection = null;
		try {
			connection = availableConnetion.take();
			unavailableConnetion.add(connection);
			logger.info("Connections: " + availableConnetion.size());
			logger.info("Unvaible: " + unavailableConnetion.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.info("Interruptedexception", e);
		}
		return connection;
	}

	public void returnConnection(ProxyConnection connection) {

		try {
			if (!connection.getAutoCommit()) {
				connection.setAutoCommit(true);
			}
			unavailableConnetion.remove(connection);
			availableConnetion.put(connection);
		} catch (InterruptedException e) {
			logger.info("Connection ploblem", e);
		} catch (SQLException e) {
			throw new RuntimeException();
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

			for (int i = 0; i < POOL_SIZE; i++) {
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
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTIES);
		try {
			property.load(inputStream);
			URL = property.getProperty(DATABASE_URL);
			user = property.getProperty(DATABASE_USER_LOGIN);
			password = property.getProperty(DATABASE_USER_PASSWORD);
		} catch (IOException e) {
			throw new NoJDBCPropertiesFileException();
		}

	}
}
