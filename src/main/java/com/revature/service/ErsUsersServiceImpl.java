package com.revature.service;

import com.revature.dao.ErsUsersDao;
import com.revature.dao.ErsUsersDaoImpl;
import com.revature.model.User;

public class ErsUsersServiceImpl implements ErsUsersService{

	private ErsUsersDao dao = new ErsUsersDaoImpl();
	
	@Override
	public boolean validateUser(String username) {
		// TODO Auto-generated method stub
		System.out.println("Validating user");
		return (dao.selectUserByUsername(username) != null);
	}

	@Override
	public User login(String user, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register(User u) {
		// TODO Auto-generated method stub
		System.out.println("Inserting user now");
		return dao.insertUser(u);
		//return false;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.selectUserByUsername(username);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return dao.selectUserById(id);
	}

}
