package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;

@WebServlet("/reimbursements")
public class ReimbursementServlet extends HttpServlet {
	
	

	static ReimbursementService reimService = new ReimbursementService();
	
	private static Logger log = Logger.getLogger(ReimbursementServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		
		User users = (User) session.getAttribute("user");
		
		int userId = users.getUserId();
		log.trace("SERVLET IN REIMBURSEMENT");
		
		//consult service for data
		List<Reimbursement> reims = reimService.getReimbursementByUserId(userId);
		
		//convert to json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reims);
		log.trace("FINDING ALL REIMBURSEMENTS IN JSON: " + json);
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		HttpSession session = req.getSession();
		
		User users = (User) session.getAttribute("user");
		
		String amount = req.getParameter("amount");
		double conAmount = Double.parseDouble(amount);
		String description = req.getParameter("description");
		String reimbType = req.getParameter("type");
		
		int typeId = typeIdCase(reimbType);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");  
	    Date date = new Date();
	
		String todayStr = "" + formatter.format(date);
		int author = users.getUserId();
		
		
		Reimbursement reimb = new Reimbursement(1, conAmount, todayStr, "", description, author, 1, 1, typeId);
		
		reimb = reimService.save(reimb);
		resp.sendRedirect("index.html");
	}
	
	private int typeIdCase (String typeStr) {
		
		switch(typeStr) {
		case "Lodging": return 1;
		case "Travel": return 2;
		case "Food": return 3;
		case "Other": return 4;
		default: System.out.println("Wrong input");
		return 0;
		}
	}
	
	private String typeString (int id) {
		
		switch(id) {
		case 1: return "Lodging";
		case 2: return "Travel";
		case 3: return "Food";
		case 4: return "Other";
		default: System.out.println("Wrong input");
		return null;
		}
	}
}
