package com.revature.service;

import com.revature.model.User;

public interface ErsUsersService {

	public boolean validateUser(String user);

	public User login(String user, String pass);
	
	public boolean register(User u);

	public User getUserByUsername(String user);
	
	public User getUserById(int id);
}
