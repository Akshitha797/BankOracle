package com.cg.bankOracle.exception;

public class LoginException extends Exception{

	public LoginException() {
		System.err.println("Invalid Username or Password");
	}
}
