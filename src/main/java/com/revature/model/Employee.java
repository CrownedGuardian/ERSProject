package com.revature.model;

public class Employee extends User{
	public Employee() {
		super();
	}
	
	public Employee(int id, String username, String password, 
					String firstName, String lastName, String email,
					int roleId) {
		super(id, username, password, firstName, lastName, email, roleId);
	}
	
	@Override
	public String toString() {
		return "Employee [Username=" + getUsername() + 
					   ", First Name=" + getFirstName() + 
					   ", Last Name=" + getLastName() +
					   ", Email Address=" + getEmail() + "]";
	}
}
