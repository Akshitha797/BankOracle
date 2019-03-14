package com.cg.bankOracle.exception;

public class ToAccountException extends Exception{

	public ToAccountException() {
		System.err.println("Account Number Does Not Exist");
	}
}
