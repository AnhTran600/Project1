package com.revature.request;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeHomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET on EmployeeHomeServlet");
		Object reqE = EmployeeRequestHelper.process(req);
		//System.out.println(req.getSession().getAttribute("loggedin"));
		if (reqE instanceof String) {
			System.out.println("GET on EmployeeHomeServlet in if statement");
			//System.out.println(reqE);
			String URI = (String) reqE;
			req.getRequestDispatcher(URI).forward(req, resp);
		}
		else {
			System.out.println("GET on EmployeeHomeServlet in else statement");
			req.getRequestDispatcher("Error.html").forward(req, resp);
		}
		//request.getRequestDispatcher("Employee.html").forward(request, response);
		// REDIRECTING
		//System.out.println("Redirecting from Login to Home");
		//resp.sendRedirect("home");
		// REDIRECTING
		//System.out.println("Redirecting from Login to Google");
		//resp.sendRedirect("https://google.com");
		//resp.sendError(504);
		//System.out.println(req.getSession().getAttribute()));
		// FORWARDING TO HTML PAGE
		//System.out.println("Forwarding to HTML page from Login");
		//req.getRequestDispatcher("login.html").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST on EmployeeHomeServlet");
		//req.getRequestDispatcher("Employee.html").forward(req, resp);
		//System.out.println(req.getSession().getAttribute("loggedin"));
		
	}
}
