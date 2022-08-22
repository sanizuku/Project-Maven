package com.cdac.service;

public class LoginService {

	public static boolean isValidUser(String username, String password) {
	if(username.equals("sandeep") && password.equals("123"))
		return true;
		return false;
	}

}
