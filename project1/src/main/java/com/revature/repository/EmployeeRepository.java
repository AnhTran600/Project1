package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeRepository {

	public Employee findEmployeeLogin(String username, String password);
	public Employee findEmployee(long eId);
	public boolean updateEmployee(long eId, long mId);
	public List<Employee> findAllEmployee();
}
