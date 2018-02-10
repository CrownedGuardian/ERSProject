package com.revature.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.service.ErsUsersService;
import com.revature.service.ErsUsersServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {
	
	static Logger logger = Logger.getLogger(LoginController.class);
	
	public static String login(HttpServletRequest req) {
		if(req.getMethod().equals("GET"))
			return "login.html";
		String username=req.getParameter("user");
		System.out.println(username);
		String password=req.getParameter("pass");
		
		ErsUsersService service = new ErsUsersServiceImpl();
		if(service.validateUser(username)) {
			System.out.println("User has validated");
			User u = service.getUserByUsername(username);
			System.out.println("User has been extracted by username");
			req.getSession().setAttribute("loggedusername", username);
			req.getSession().setAttribute("loggedpassword", password);
			req.getSession().setAttribute("roleid", u.getRoleId());
			BasicConfigurator.configure();
			logger.info("Login success logged");
			return "html/dashboard.html";
		} else {
			System.out.println("User was invalidated");
			BasicConfigurator.configure();
			logger.info("Login failure logged");
			return "login.html";
		}
	}
	
	public static String loginAfterRegistration(HttpServletRequest req) {
		if(req.getMethod().equals("GET"))
			return "login.html";
		String username=req.getParameter("user");
		System.out.println(username);
		String password=req.getParameter("pass");
		
		ErsUsersService service = new ErsUsersServiceImpl();
		if(service.validateUser(username)) {
			System.out.println("User has validated");
			User u = service.getUserByUsername(username);
			System.out.println("User has been extracted by username");
			req.getSession().setAttribute("loggedusername", username);
			req.getSession().setAttribute("loggedpassword", password);
			req.getSession().setAttribute("roleid", u.getRoleId());
			BasicConfigurator.configure();
			logger.info("Login success logged");
			return "dashboard.html";
		} else {
			System.out.println("User was invalidated");
			BasicConfigurator.configure();
			logger.info("Login failure logged");
			return "login.html";
		}
	}
	
	public static String getRoleId(HttpServletRequest req) {
		System.out.println("getting role id");
		BasicConfigurator.configure();
		logger.info("Role ID info extracted");
		if(req.getSession().getAttribute("loggedusername") != null) {
			System.out.println(req.getSession().getAttribute("loggedusername").toString());
			ErsUsersService service = new ErsUsersServiceImpl();
			if(service.validateUser(req.getSession().getAttribute("loggedusername").toString())) {
				System.out.println(req.getSession().getAttribute("roleid").toString());
				return req.getSession().getAttribute("roleid").toString();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public static String logout(HttpServletRequest req) {
			req.getSession().invalidate();
			BasicConfigurator.configure();
			logger.info("Logout logged");
			return "login.html";
	}
}
