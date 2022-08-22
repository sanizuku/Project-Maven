package com.cdac.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.service.RegisterService;


@WebServlet("/register2.cdac")
public class registrationservlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		int mobileno=Integer.parseInt(request.getParameter("mobileNo"));
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("password");
		
		PrintWriter out = response.getWriter();
		out.write("<html><body>");
		
		RegisterService registerService = new RegisterService();
		boolean present=registerService.isCustomerPresent(email);
		if(present) {
			out.write("<h1>It seems you are already registered with us");
		}
		else {
			registerService.register(name, email, mobileno, username, password);
			out.write("<h1>REgistration successfull<h1>");
		}
		out.write("</body></html>");
	}

}
