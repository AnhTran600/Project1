package com.revature.eval;
//import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertFalse;


//import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepositoryJdbc;

public class Evaluate {

	@Test
	public void employeeLoginEval() {
		final String enterUsername = "employee";
		final String enterPassword = "password";
		System.out.println(enterUsername+enterPassword);
		EmployeeRepositoryJdbc employeeJdbc = EmployeeRepositoryJdbc.getEmployeeDaoJdbc();
		Employee tester = employeeJdbc.findEmployeeLogin("employee","password");
		System.out.println(tester);
			
		
	}
	
	@Test
	public void employeeListEval() {
		EmployeeRepositoryJdbc employeeJdbc = EmployeeRepositoryJdbc.getEmployeeDaoJdbc();
		List<Employee> allList = employeeJdbc.findAllEmployee();
		for (Employee a : allList) System.out.println(a);
		Employee tester2 = employeeJdbc.findEmployee(2);
		System.out.println(tester2);
		employeeJdbc.updateEmployee(2, 1);
	}
}
