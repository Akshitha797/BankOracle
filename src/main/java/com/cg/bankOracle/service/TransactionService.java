package com.cg.bankOracle.service;

import com.cg.bankOracle.beans.CustomerDetails;
import com.cg.bankOracle.beans.TransactionDetails;

public interface TransactionService {

	public CustomerDetails deposit(long accountNumber,double amount);
	public CustomerDetails withdrawal(long accountNumber,double amount);
	public CustomerDetails showBalance(long accountNumber);
	public int fundTransfer(TransactionDetails transactionDetails);

}
