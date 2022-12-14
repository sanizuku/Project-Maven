package com.cdac.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterService {
	public boolean isCustomerPresent(String email) {
		
	Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//read about Reflection api Class.forname
		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac","sandeep","welcome123");
		boolean present=false;
		   PreparedStatement pst= conn.prepareStatement("select count(*) from tbl_customer where email=?");
		   pst.setString(1,email);
		   ResultSet rs=pst.executeQuery();
		   if(rs.next()){
		   int c=rs.getInt(1);
		   if(c==1)
		   present=true;
		   }
		   return present;
	}
	catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		return false;
	}
		finally {
			try {conn.close();} catch(Exception e) {}
		}

}
	public void register(String name,String email,int mobileno,String username, String password) {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//read about Reflection api Class.forname
		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac","sandeep","welcome123");
		 PreparedStatement st=conn.prepareStatement("insert into tbl_customer(name,email,mobileno,username,password) values(?,?,?,?,?)");
			
		    st.setString(1, name);
		    st.setString(2, email);
		    st.setInt(3, mobileno);
		    st.setString(4, username);
		    st.setString(5, password);
		    st.executeUpdate();
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		
		}
			finally {
				try {conn.close();} catch(Exception e) {}
	}
}
}
