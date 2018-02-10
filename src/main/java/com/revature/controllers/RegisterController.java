package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.service.ErsUsersService;
import com.revature.service.ErsUsersServiceImpl;

public class RegisterController {
	
	static Logger logger = Logger.getLogger(RegisterController.class);
	
	public static String register(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			System.out.println("Apparently, the GET method was used when trying to register");
			return "register.html";
		}
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String username = req.getParameter("confirmUser");
		String password = req.getParameter("confirmPass");
		String email = req.getParameter("email");
		Integer roleId = Integer.parseInt(req.getParameter("role"));
		
		ErsUsersService service = new ErsUsersServiceImpl();
		if(service.validateUser(username)) {
			System.out.println("User already exists");
			return "register.html";
		} else {
			User u = new User();
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setUsername(username);
			u.setPassword(password);
			u.setEmail(email);
			u.setRoleId(roleId);
			System.out.println("About to register");
			service.register(u);
		}
		BasicConfigurator.configure();
		logger.info("Registration logged");
		System.out.println("Time to login after registering");
		return "login.html";
	}
}
