package com.revature.repository;

import com.revature.model.Manager;

public interface ManagerRepository {

	public Manager findManagerLogin(String username, String password);
	
}
