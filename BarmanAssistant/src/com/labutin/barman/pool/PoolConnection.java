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
	private static final String DATABASE_URL_KEY = "db.URL";
	private static final String DATABASE_USER_LOGIN_KEY = "db.Login";
	private static final String DATABASE_USER_PASSWORD_KEY = "db.Password";
	private static final String DATABASE_PROPERTIES_PATH = "resources/DataBase.properties";
	private String user;
	private String databaseURL;
	private String password;
	private static final int POOL_SIZE = 32;
	private static Logger logger = LogManager.getLogger();
	private AtomicBoolean isCreated = new AtomicBoolean(false);

	public AtomicBoolean isCreated() {
		return isCreated;
	}

	public ProxyConnection getConnection() {
		ProxyConnection connection = null;
		try {
			connection = availableConnetion.take();
			unavailableConnetion.add(connection);
		} catch (InterruptedException e) {
			logger.info("Interrupted exception", e);
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
		} catch (InterruptedException | SQLException e) {
			logger.info("Connection ploblem", e);
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
				DriverManager.registerDriver(DriverManager.getDriver(databaseURL));
			} catch (SQLException e) {
				throw new NoJDBCDriverException(e);
			}

			for (int i = 0; i < POOL_SIZE; i++) {
				try {
					ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(databaseURL, user, password));
					availableConnetion.add(connection);
				} catch (SQLException e) {
					throw new NoJDBCDriverException(e);
				}
			}
			isCreated.set(true);
		}
	}

	private void setConfing() throws NoJDBCPropertiesFileException {
		Properties property = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_PATH);
		try {
			property.load(inputStream);
			databaseURL = property.getProperty(DATABASE_URL_KEY);
			user = property.getProperty(DATABASE_USER_LOGIN_KEY);
			password = property.getProperty(DATABASE_USER_PASSWORD_KEY);
		} catch (IOException e) {
			throw new NoJDBCPropertiesFileException(e);
		}

	}

	public void closePool() {
		ProxyConnection connection;
		for (int i = 0; i < POOL_SIZE; i++) {
			try {
				connection = availableConnetion.take();
				connection.closeInPool();
			} catch (InterruptedException | SQLException e) {
				logger.warn("Close pool problem",e);
			} 

		}
		deregisterDriver();
	}

	private void deregisterDriver() {
		DriverManager.drivers().forEach(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				logger.warn("DriverManager cannot be deregister", e);
			}
		});
	}

}
