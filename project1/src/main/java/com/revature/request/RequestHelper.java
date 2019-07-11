package com.revature.request;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

	private RequestHelper() {}
	
	public static Object process(HttpServletRequest req) {
		switch (req.getRequestURI()) {
		case "/project1/main":
			return "Login.html";
		default:
			return "Error.html";
		}
		
	}
}
