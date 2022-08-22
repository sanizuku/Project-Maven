package com.cdac.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register.cdac")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	int mobileno=Integer.parseInt(request.getParameter("mobileNo"));
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	System.out.println("password");
	//Encoding
	Base64.Encoder encoder=Base64.getMimeEncoder();
	String password1=encoder.encodeToString(password.getBytes());
	System.out.println(password1);
	
	//jdbc code to insert the data
	//todo: create table tbl_customer(id int primary key auto_increment,
    // name varchar(20),email varchar(30),mobileno int,username varchar(15),password varchar(8));
	//also we need download jdbl driver for mysql and then
	//copy the mysql-connector-java-...jar to WEB-INF/lib folder of our project
	//toDo:store the password in encoded form in the dtabase (use base64 encoding)
	//ToDo:before inserting ,you will check by firing a select query
	//whether ther is already a customer registered by the same email address
	// if yes ,then another record should not get inserted
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");//read about Reflection api Class.forname
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac","sandeep","welcome123");
		/*
		 * boolean is CustomerPresent=false;
		 * preparedStatement pst= conn.prepareStatement("select count(*) from tbl_customer where email=?");
		 * pst.setString(1,email);
		 * ResultSet rs=pst.executeQuery();
		 * if(rs.next()){
		 * int c=rs.getInt(1);
		 *  if(c==1)
		 *  isCustomerPresent=true;
		 *  }
		 *  if(!isCustomerPresent){
		 */
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("Select email from tbl_customer");
		//extract data from result set
		boolean emailCheck=false;
		
		while(rs.next()) {
			String email1=rs.getString("email");
			System.out.println(email1);
			if(email!=null && email1.equals(email)) {
				emailCheck = true;
			}
		}
		System.out.println(emailCheck);
		
		if(emailCheck) {
			out.write("<html><body>");
			out.write("<h1>EmailId already exists</h1>");
		}
		else {
			PreparedStatement st=conn.prepareStatement("insert into tbl_customer(name,email,mobileno,username,password) values(?,?,?,?,?)");
			
		    st.setString(1, name);
		    st.setString(2, email);
		    st.setInt(3, mobileno);
		    st.setString(4, username);
		    st.setString(5, password1);
		    st.executeUpdate();
		    conn.close();
		    out.write("<html><body>");
			out.write("<h1>Registration successfull!</h1>");
			out.write("</body></html>");
			
		}
	
	}
	catch(Exception e) {
		e.printStackTrace();
		out.write("<html><body>");
		out.write("<h1>Registration unsuccessful</h1>");
		out.write("</body></html>");
	}
	
	//response.sendRedirect("thankyou.html"); not use we canwrite html in servlet also
	//gettinf mime decoder
	Base64.Decoder decoder = Base64.getMimeDecoder();
	//decoding mime encoded message
	String password2=new String(decoder.decode(password1));
	System.out.println("Decoded password: " + password2);
	
	
	
	}

}
