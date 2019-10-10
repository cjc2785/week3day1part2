package com.ss.servlet;

import com.ss.dto.*;
import com.google.gson.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> users = new ArrayList<User>();
		
		users.add(new User("joe", "joepass"));
		users.add(new User("mike", "mikepass"));
		users.add(new User("al", "alpass"));
		users.add(new User("dan", "danpass"));
		
		
        Gson gson = new Gson();
        
        User reqUser = (User) gson.fromJson(request.getReader(), User.class);
        
   	
		Optional<User> found = users.stream()
				.filter(user -> (user.getUsername().equals(reqUser.getUsername())) &&
						(user.getPassword().equals(reqUser.getPassword()))) 
				.findAny();
		
		if(found.isEmpty()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		response.setStatus(200);
		PrintWriter out = response.getWriter();
		
		out.print("success");
		out.flush();
	}
}
