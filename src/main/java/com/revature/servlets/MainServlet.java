package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ErsReimbursementService;
import com.revature.service.ErsReimbursementServiceImpl;

public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 32225075470975451L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("In MainServlet doGet method");

		if (req.getRequestURI().contains(".do")) {
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			String jsonString = RequestHelper.process(req);
			System.out.println(jsonString);
			out.write(jsonString);
		} else {
			System.out.println("In doGet: " + req.getRequestURI());
			req.getRequestDispatcher(RequestHelper.process(req)).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("In MainServlet doPost method");
		if (req.getRequestURI().contains(".do")) {
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			String jsonString = RequestHelper.process(req);
			System.out.println(jsonString);
			out.write(jsonString);
		} else {
			System.out.println("In doPost: " + req.getRequestURI());
			req.getRequestDispatcher(RequestHelper.process(req)).forward(req, resp);
		}
	}

}
