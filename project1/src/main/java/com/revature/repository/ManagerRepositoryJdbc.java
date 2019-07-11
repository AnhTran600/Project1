package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerRepositoryJdbc implements ManagerRepository {

	private static final Logger LOGGER = Logger.getLogger(ManagerRepositoryJdbc.class);
	private static ManagerRepositoryJdbc managerDaoJdbc;
	
	private ManagerRepositoryJdbc() {
		
	}
	
	public static ManagerRepositoryJdbc getManagerDaoJdbc() {
		if(managerDaoJdbc == null) {
			managerDaoJdbc = new ManagerRepositoryJdbc();
		}
		return managerDaoJdbc;
	}
	@Override
	public Manager findManagerLogin(String username, String password) {
		LOGGER.trace("Entering findManagerLogin method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM MANAGER WHERE MANAGER_USERNAME = ? AND MANAGER_PASSWORD = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return new Manager (
						result.getLong("MANAGER_ID"),
						result.getString("MANAGER_FIRSTNAME"),
						result.getString("MANAGER_LASTNAME"),
						result.getString("MANAGER_USERNAME"),
						result.getString("MANAGER_PASSWORD"),
						result.getString("MANAGER_GENDER")
						);
			}
		}
		catch (SQLException exc) {
			LOGGER.error("Could not find Manager Login.", exc);
		}
		return null;
	}
	/*
	public static void main(String[] args) {
		ManagerRepositoryJdbc managerJdbc = new ManagerRepositoryJdbc();
		Manager test = managerJdbc.findManagerLogin("MANAGER","PASSWORD");
		System.out.println(test);
	}
	*/
}
