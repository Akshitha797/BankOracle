package com.cg.bankOracle.service;

import com.cg.bankOracle.beans.TransactionDetails;

public interface TransactionService {

	public double deposit(long accountNumber,double amount);
	public double withdrawal(long accountNumber,double amount);
	public double showBalance(long accountNumber);
	public int fundTransfer(TransactionDetails transactionDetails);

}
