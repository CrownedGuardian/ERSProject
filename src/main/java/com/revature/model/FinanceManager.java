package com.revature.model;

public class FinanceManager extends User{
	public FinanceManager() {
		super();
	}
	
	public FinanceManager(int id, String username, String password, 
					String firstName, String lastName, String email,
					int roleId) {
		super(id, username, password, firstName, lastName, email, roleId);
	}
	
	@Override
	public String toString() {
		return "FinanceManager [Username=" + getUsername() + 
					   ", First Name=" + getFirstName() + 
					   ", Last Name=" + getLastName() +
					   ", Email Address=" + getEmail() + "]";
	}
}
