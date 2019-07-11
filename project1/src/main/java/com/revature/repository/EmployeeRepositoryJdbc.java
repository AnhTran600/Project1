package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeRepositoryJdbc implements EmployeeRepository {

	private static final Logger LOGGER = Logger.getLogger(EmployeeRepositoryJdbc.class);
	private static EmployeeRepositoryJdbc employeeDaoJdbc;
	
	private EmployeeRepositoryJdbc() {
		
	}
	
	public static EmployeeRepositoryJdbc getEmployeeDaoJdbc() {
		if(employeeDaoJdbc == null) {
			employeeDaoJdbc = new EmployeeRepositoryJdbc();
		}
		return employeeDaoJdbc;
	}
	@Override
	public Employee findEmployeeLogin(String username, String password) {
		LOGGER.trace("Entering findEmployeeUsername method with parameters.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_USERNAME = ? AND EMPLOYEE_PASSWORD = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return new Employee (
						result.getLong("EMPLOYEE_ID"),
						result.getString("EMPLOYEE_FIRSTNAME"),
						result.getString("EMPLOYEE_LASTNAME"),
						result.getString("EMPLOYEE_USERNAME"),
						result.getString("EMPLOYEE_PASSWORD"),
						result.getString("EMPLOYEE_GENDER"),
						result.getLong("EMPLOYEE_MANAGER")
						);
			}
		}
		catch (SQLException exc) {
			LOGGER.error("Could not find Employee Login.", exc);
		}
		
		return null;
	}

	@Override
	public List<Employee> findAllEmployee() {
		LOGGER.trace("Entering findAllEmployee method without parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE ORDER BY EMPLOYEE_ID";
			PreparedStatement statement = connect.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			List<Employee> employees = new ArrayList<>();
			while (result.next()) {
				employees.add(new Employee (
						result.getLong("EMPLOYEE_ID"),
						result.getString("EMPLOYEE_FIRSTNAME"),
						result.getString("EMPLOYEE_LASTNAME"),
						result.getString("EMPLOYEE_USERNAME"),
						result.getString("EMPLOYEE_PASSWORD"),
						result.getString("EMPLOYEE_GENDER"),
						result.getLong("EMPLOYEE_MANAGER")
						));
			}
			return employees;
		}
		catch (SQLException exc) {
			LOGGER.error("Could not findAllEmployee.", exc);
		}
		
		return null;
	}

	@Override
	public boolean updateEmployee(long eId, long mId) {
		LOGGER.trace("Entering updateEmployee method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_MANAGER = ? WHERE EMPLOYEE_ID = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, mId);
			statement.setLong(2, eId);
			if (statement.executeUpdate() > 0) {
				System.out.println("update employee success!!");
				return true;
			}
		}
		catch (SQLException exc) {
			LOGGER.error("Could not update Employee Manager.", exc);
		}
		return false;
	}

	@Override
	public Employee findEmployee(long id) {
		LOGGER.trace("Entering findEmployee method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				 return new Employee (
						result.getLong("EMPLOYEE_ID"),
						result.getString("EMPLOYEE_FIRSTNAME"),
						result.getString("EMPLOYEE_LASTNAME"),
						result.getString("EMPLOYEE_USERNAME"),
						result.getString("EMPLOYEE_PASSWORD"),
						result.getString("EMPLOYEE_GENDER"),
						result.getLong("EMPLOYEE_MANAGER")
						);
			}
		}
		catch (SQLException exc) {
			LOGGER.error("Could not find Employee Username.", exc);
		}
		return null;
	}
	/*
	public static void main(String[] args) {
		EmployeeRepositoryJdbc employeeJdbc = new EmployeeRepositoryJdbc();
		String username = "employee";
		String password = "password";
		Employee checkEm = employeeJdbc.findEmployeeLogin(username, password);
		System.out.println(checkEm);
		
		List<Employee> allList = employeeJdbc.findAllEmployee();
		for (Employee a : allList) System.out.println(a);
		
		employeeJdbc.updateEmployee(2, 3);
		
		Employee testFind = employeeJdbc.findEmployee(2);
		System.out.println(testFind);
		
	}
	*/
}
