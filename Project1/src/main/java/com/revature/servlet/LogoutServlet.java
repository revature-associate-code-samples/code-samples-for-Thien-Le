package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	
	private static Logger log = Logger.getLogger(LogoutServlet.class);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		HttpSession session = req.getSession(false);
		
		String toTrace = "LOGGING OUT SESSION WITH ATTRIBUTES: \n";
		
		
		Enumeration<String> atts = session.getAttributeNames();
		while(atts.hasMoreElements()) {
			toTrace += atts.nextElement() + "\n";
		}
		log.trace(toTrace);
		
		
		//invalidate session
		if(session != null) {
			session.invalidate();
			log.trace("INVALIDATING SESSION: " + session.getId());
			
		}
		
		
				
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {

		
		
		HttpSession session = req.getSession(false);
	
		String toTrace = "LOGGING OUT SESSION WITH ATTRIBUTES: \n";
	
	
	Enumeration<String> atts = session.getAttributeNames();
	while(atts.hasMoreElements()) {
		toTrace += atts.nextElement() + "\n";
	}
	log.trace(toTrace);
	
	
	//invalidate session
	if(session != null) {
		session.invalidate();
		log.trace("INVALIDATING SESSION: " + session.getId());
	
		//resp.sendRedirect("login.html");
		
	}
	
	req.getRequestDispatcher("login.html").forward(req, resp);
	}
}
