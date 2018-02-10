package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.LoginController;
import com.revature.controllers.RegisterController;
import com.revature.controllers.ReimbursementController;

public class RequestHelper {
	public static String process(HttpServletRequest req) throws IOException {
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/ExpenseReimbursementSystemProject/register.go.dash":
				System.out.println("Time to register");
				return RegisterController.register(req);
			case "/ExpenseReimbursementSystemProject/login.go.dash":
				System.out.println("Time to login");
				return LoginController.login(req);
			case "/ExpenseReimbursementSystemProject/managerView.go.dash":
				return "html/manager_dashboard.html";
			case "/ExpenseReimbursementSystemProject/employeeView.go.dash":
				return "html/employee_dashboard.html";
			case "/ExpenseReimbursementSystemProject/requestReimbursement.go.dash":
				return "html/reimbursement_request.html";
			case "/ExpenseReimbursementSystemProject/userCred.do.dash":
				return ReimbursementController.getUserCredentials(req);
			case "/ExpenseReimbursementSystemProject/getMyReimbursements.do.dash":
				return ReimbursementController.getReimbursements(req);
			case "/ExpenseReimbursementSystemProject/viewReimbursements.go.dash":
				return "html/reimbursements_display.html";
			case "/ExpenseReimbursementSystemProject/getReimbursements.do.dash":
				return ReimbursementController.getReimbursements(req);
			case "/ExpenseReimbursementSystemProject/addReimbursement.do.dash":
				return ReimbursementController.createReimbursement(req);
			case "/ExpenseReimbursementSystemProject/approveReimbursement.go.dash":
				return ReimbursementController.approveReimbursement(req);
			case "/ExpenseReimbursementSystemProject/denyReimbursement.go.dash":
				return ReimbursementController.denyReimbursement(req);
			case "/ExpenseReimbursementSystemProject/logout.go.dash":
				return LoginController.logout(req);
			case "/ExpenseReimbursementSystemProject/html/logout.go.dash":
				return LoginController.logout(req);
			case "/ExpenseReimbursementSystemProject/html/denyReimbursement.go.dash":
				return ReimbursementController.denyReimbursement(req);
			case "/ExpenseReimbursementSystemProject/html/approveReimbursement.go.dash":
				return ReimbursementController.approveReimbursement(req);
			case "/ExpenseReimbursementSystemProject/html/addReimbursement.do.dash":
				return ReimbursementController.createReimbursement(req);
			case "/ExpenseReimbursementSystemProject/html/getReimbursements.do.dash":
				return ReimbursementController.getReimbursements(req);
			case "/ExpenseReimbursementSystemProject/html/viewReimbursements.go.dash":
				return "reimbursements_display.html";
			case "/ExpenseReimbursementSystemProject/html/getMyReimbursements.do.dash":
				return ReimbursementController.getReimbursements(req);
			case "/ExpenseReimbursementSystemProject/html/requestReimbursement.go.dash":
				return "reimbursement_request.html";
			case "/ExpenseReimbursementSystemProject/html/userCred.do.dash":
				return ReimbursementController.getUserCredentials(req);
			case "/ExpenseReimbursementSystemProject/html/login.go.dash":
				System.out.println("Time to login");
				return LoginController.loginAfterRegistration(req);
			case "/ExpenseReimbursementSystemProject/html/managerView.go.dash":
				return "manager_dashboard.html";
			case "/ExpenseReimbursementSystemProject/html/employeeView.go.dash":
				return "employee_dashboard.html";
			case "/ExpenseReimbursementSystemProject/html/register.go.dash":
				System.out.println("Time to register");
				return RegisterController.register(req);
			default:
				System.out.println("something went wrong with the URI");
				//System.out.println(req.getRequestURI());
				return "login.html";
		}
	}
}
