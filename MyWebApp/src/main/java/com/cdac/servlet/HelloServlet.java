package com.cdac.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello.cdac")//url pattern  //we can change name so thar nobody can guess url security Abstraction hiding implementation details
public class HelloServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.write("<html><body>");
		out.write("<h1>Hello Again!</h1>");
		out.write("<h2>Today's Date is " + LocalDate.now()+"</h2>");
		out.write("</body></html>");
	}

}
