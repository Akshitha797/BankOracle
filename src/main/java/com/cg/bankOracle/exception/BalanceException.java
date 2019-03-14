package com.cg.bankOracle.exception;

public class BalanceException extends Exception{

	public BalanceException() {
		System.err.println("Insufficient Balance");
	}
}
