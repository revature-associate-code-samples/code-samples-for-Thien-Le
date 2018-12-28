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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.User;
import com.revature.service.UserService;
import org.apache.log4j.Logger;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	
	static UserService uService = new UserService();
	
	private static Logger log = Logger.getLogger(UserServlet.class);
	
	//retrieve ALL users as JSON string
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//log.trace("SERVLET IN USERSERVLET");
		
		HttpSession session = req.getSession();
		
		User users = (User) session.getAttribute("user");
		
		
		//convert to json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(users);
		//log.trace("FINDING 1 USER in JSON: " + json);
		
		//send response
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		User u = mapper.readValue(req.getInputStream(), User.class);
		
		u = uService.getUserById(u.getUserId());
		
		String json = mapper.writeValueAsString(u);
	
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(json);
	}
}
