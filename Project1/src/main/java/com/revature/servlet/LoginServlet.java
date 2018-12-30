package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.User;
import com.revature.service.UserService;


//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	static UserService uService = new UserService();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		// functionality to go back to login.html 
				//REQUEST DISPATCHER
		req.getRequestDispatcher("login.html").forward(req, resp);
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//login functionality here
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
	
		//consult user service to obtain User with this info
		User user = uService.validateUser(username, password);
		
		
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		
		if(user == null) {
			
			req.getRequestDispatcher("error-login.html").forward(req, resp);
			
		}
		else {
			
			int roleId = user.getRoleId();
			//successful login
			//add user to session
			HttpSession session = req.getSession();
			//create new session and returns it if none exists
			session.setAttribute("user", user);
			
			if(roleId == 3) {
				resp.sendRedirect("index.html");
			} else if(roleId == 1) {
				resp.sendRedirect("manIndex.html");
			}
			
		}
		
	
	}
}
