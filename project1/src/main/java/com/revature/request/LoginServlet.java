package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.repository.EmployeeRepositoryJdbc;
import com.revature.repository.ManagerRepositoryJdbc;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET on LoginServlet");
		Object reqq = RequestHelper.process(req);
		if (reqq instanceof String) {
			System.out.println(reqq);
			String URI = (String) reqq;
			req.getRequestDispatcher(URI).forward(req, resp);
		}
		else {
			System.out.println("GET on LoginServlet in else statement");
			req.getRequestDispatcher("Error.html").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST on LoginServlet");
		Employee checkEm = EmployeeRepositoryJdbc.getEmployeeDaoJdbc().findEmployeeLogin(req.getParameter("username"), req.getParameter("password"));
		if (checkEm != null) {
			req.getSession().setAttribute("loggedin", checkEm);
			req.getRequestDispatcher("Employee.html").forward(req, resp);
		}
		else if (checkEm == null) {
			Manager checkMa = ManagerRepositoryJdbc.getManagerDaoJdbc().findManagerLogin(req.getParameter("username"), req.getParameter("password"));
			if (checkMa != null) {
				req.getSession().setAttribute("loggedin", checkMa);
				req.getRequestDispatcher("Manager.html").forward(req, resp);
			}
			else if (checkMa == null) {
				req.getRequestDispatcher("Login2.html").forward(req, resp);
			}
		}
	}
}
