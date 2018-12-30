package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {

	static ReimbursementService reimbService = new ReimbursementService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		
		//consult service for data
		List<Reimbursement> reimb = reimbService.getAllReimbursements();
		
		//convert to json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reimb);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		
		HttpSession session = req.getSession();
		
		User users = (User) session.getAttribute("user");
		
		String option = req.getParameter("option");
		String status = req.getParameter("decision");
		int statusId = getStatusId(status);
		
		int reimbId = getReimbId(option);
	
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");  
	    Date date = new Date();
	
		String todayStr = "" + formatter.format(date);
		int resolverId = users.getRoleId();
		
		Reimbursement reimb = new Reimbursement(reimbId, 20.00, "", todayStr, "", 1, resolverId, statusId, 1);
		reimb = reimbService.updateReimb(reimb);
		resp.sendRedirect("manIndex.html");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String optionNumber = req.getParameter("option");
		String checkbox = req.getParameter("decision");
		System.out.println(optionNumber + checkbox);
		
		
		resp.sendRedirect("manIndex.html");
		
	}
	
	private int getReimbId(String str) {
		
		String result = "";
		
		String splitArray[] = str.split(" ");
		result = splitArray[1].substring(0, splitArray[1].length()-1);
		return Integer.parseInt(result);
	}
	
	private int getStatusId (String statusStr) {
		
		switch(statusStr) {
		case "approve": return 2;
		case "deny": return 3;
		default: System.out.println("Wrong statusId input");
		return 0;
		}
	}
}
