package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class);
	static {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e) {
			LOGGER.warn("Exception thrown adding oracle driver.", e);
		}

	}
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@admin.c3t4snnggml8.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "admin";
		String password = "password";
		return DriverManager.getConnection(url, username, password);
	}
	/*
	 * testing connection
	public static void main(String[] args) {
		try {
			getConnection();
			LOGGER.info("Connected");
		}
		catch (SQLException exc) {
			LOGGER.error("Not Connected", exc);
		}
	}
	*/
}
