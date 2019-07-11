package com.revature.request;

import javax.servlet.http.HttpServletRequest;

import java.io.FileWriter;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.model.Employee;

public class EmployeeRequestHelper {

	private EmployeeRequestHelper() {}
	
	public static Object process(HttpServletRequest req) throws IOException {
		System.out.println("process in EmployeeRequestHelper");
		System.out.println(req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/project1/viewmyinfo.do":
			System.out.println("switch in EmployeeRequestHelper");
			//String path = pathObject.getClass().getClassLoader().getResource(".").getPath();
			ObjectMapper mapper = new ObjectMapper();
	        mapper.enable(SerializationFeature.INDENT_OUTPUT);
			Employee LoggedInEmployee = (Employee) req.getSession().getAttribute("loggedin");
			String postJson = mapper.writeValueAsString(LoggedInEmployee);
			System.out.println(LoggedInEmployee);
	        FileWriter fileWriter = new FileWriter("C:/Users/Welcome/Repositories/project1/src/main/webapp/employee.json");
	        fileWriter.write(postJson);
	        fileWriter.close();
			return "EmployeeInfo.html";
		case "/project1/updatemyinfo.do":
			System.out.println("switch in EmployeeRequestHelper");
			return "EmployeeInfo.html";
		default:
			return "Error.html";
		}
	}
	
}
