package com.cdac.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddSub")
public class AddSubServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		double num1=Integer.parseInt(request.getParameter("num1"));
		double num2=Integer.parseInt(request.getParameter("num2"));
		String str=request.getParameter("calc");
		
		
		double result=0;
	
		if(str.equals("+")) {
			result=num1+num2;
			
		}
		
		else if	(str.equals("-")) {
		
			
			result=num1-num2;
			
		}
		else if(str.equals("/")) {
			result=num1/num2;
	
			
		}
		else
			result=num1*num2;

		response.setContentType("text/html");
		PrintWriter out1=response.getWriter();
		out1.write("<html><body>");
		out1.write("<h1> Answer is " +result +"</h1>");
		out1.write("</body></html>");
		

	}

}
