package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ErsReimbursementService;
import com.revature.service.ErsReimbursementServiceImpl;
import com.revature.service.ErsUsersService;
import com.revature.service.ErsUsersServiceImpl;

public class ReimbursementController {
	
	static Logger logger = Logger.getLogger(ReimbursementController.class);
	
	public static String createReimbursement(HttpServletRequest req) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		System.out.println("JSON STRING: " + json);

		ObjectMapper mapper = new ObjectMapper();
		String[] userInfo = mapper.readValue(json, String[].class);
		String username = userInfo[0];
		int roleid = Integer.parseInt(userInfo[1]);
		double amount = Double.parseDouble(userInfo[2]);
		int reimbType = Integer.parseInt(userInfo[3]);
		String description = userInfo[4];

		ErsReimbursementService service = new ErsReimbursementServiceImpl();
		ErsUsersService userService = new ErsUsersServiceImpl();
		User u = userService.getUserByUsername(username);

		Reimbursement r = new Reimbursement();
		r.setAmount(amount);
		r.setDescription(description);
		r.setStatusId(1);
		r.setTypeId(reimbType);
		r.setAuthorId(u.getUserId());
		r.setTimeSubmitted(new Timestamp(System.currentTimeMillis()));
		
        BasicConfigurator.configure();
		
		if(service.addReimbursement(r)) {
			System.out.println("Reimbursement insertion successful");
			logger.info("Insertion has been logged");
		} else {
			System.out.println("Reimbursement insertion unsuccessful");
		}
		
		List<Reimbursement> temp = service.getReimbursementsByAuthorUsername(username);

		String reimbJSON = mapper.writeValueAsString(temp);
		System.out.println("JSON: " + reimbJSON);
		return reimbJSON;
	}
	
	public static String getUserCredentials(HttpServletRequest req) throws JsonProcessingException {
		//Map<String, String> userCred = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		User u = new ErsUsersServiceImpl().getUserByUsername(req.getSession().getAttribute("loggedusername").toString());
		
		BasicConfigurator.configure();
		logger.info("User credentials extracted");
		
		String userJSON = mapper.writeValueAsString(u);
		return userJSON;
	}
	
	public static String getReimbursements(HttpServletRequest req) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		System.out.println("JSON STRING: " + json);

		ObjectMapper mapper = new ObjectMapper();
		String[] userInfo = mapper.readValue(json, String[].class);
		String username = userInfo[0];
		int roleid = Integer.parseInt(userInfo[1]);
		String status = userInfo[2];

		ErsReimbursementService service = new ErsReimbursementServiceImpl();

		List<Reimbursement> temp;
		
		switch(roleid) {
			case 1:
				temp = service.getReimbursementsByAuthorUsername(username);
				break;
			case 2:
				if(status.equals("UNKNOWN")) {
					temp = service.getAllReimbursements();
				} else {
					temp = service.getReimbursementsByStatus(status);
				}
				break;
			default:
				temp = null;
				break;
		}
		
		if (temp == null) {
			System.out.println("temp is null");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("listOfReimbursements", temp);
		}
		
		BasicConfigurator.configure();
		logger.info("Reimbursements extracted");

		String reimbJSON = mapper.writeValueAsString(temp);
		System.out.println("JSON: " + reimbJSON);
		return reimbJSON;
	}
	
	public static String approveReimbursement(HttpServletRequest req) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		System.out.println("JSON STRING: " + json);

		ObjectMapper mapper = new ObjectMapper();
		String[] userInfo = mapper.readValue(json, String[].class);
		int reimbId = Integer.parseInt(userInfo[0]);
		int userId = Integer.parseInt(userInfo[1]);

		ErsReimbursementService reimbService = new ErsReimbursementServiceImpl();

		Reimbursement r = reimbService.getReimbursementById(reimbId);
		r.setResolverId(userId);
		r.setStatusId(2);
		r.setTimeResolved(new Timestamp(System.currentTimeMillis()));
		
		if(reimbService.updateReimbursement(r)) {
			System.out.println("Update successful");
			BasicConfigurator.configure();
			logger.info("Reimbursement approved");
		}
		
		return "html/manager_dashboard.html";
		
	}
	
	public static String denyReimbursement(HttpServletRequest req) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		System.out.println("JSON STRING: " + json);

		ObjectMapper mapper = new ObjectMapper();
		String[] userInfo = mapper.readValue(json, String[].class);
		int reimbId = Integer.parseInt(userInfo[0]);
		int userId = Integer.parseInt(userInfo[1]);

		ErsReimbursementService reimbService = new ErsReimbursementServiceImpl();

		Reimbursement r = reimbService.getReimbursementById(reimbId);
		r.setResolverId(userId);
		r.setStatusId(3);
		r.setTimeResolved(new Timestamp(System.currentTimeMillis()));
		
		if(reimbService.updateReimbursement(r)) {
			System.out.println("Update successful");
			BasicConfigurator.configure();
			logger.info("Reimbursement denied");
		}
		return "html/manager_dashboard.html";
	}
}
