package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface ErsUsersDao {
	//CREATE
	public boolean insertUser(User u);
	//READ
	public List<User> selectAllUsers();
	public User selectUserById(int id);
	public User selectUserByUsername(String username);
	public List<User> selectUsersByFirstName(String name);
	public List<User> selectUsersByLastName(String name);
	public User selectUserByEmail(String email);
	public List<User> selectUsersByRole(String role);
	//UPDATE
	public boolean updateUser(User u);
	//DELETE
	public boolean deleteUser(User u);
}
