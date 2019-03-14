package com.cg.bankOracle.service;

import com.cg.bankOracle.beans.TransactionDetails;
import com.cg.bankOracle.dao.TransactionDao;
import com.cg.bankOracle.dao.TransactionDaoImpl;

public class TransactionServiceImpl implements TransactionService{
	
	TransactionDao transactionDao=new TransactionDaoImpl();

	public double deposit(long accountNumber,double amount) {
		return transactionDao.deposit(accountNumber, amount);
	}

	public double withdrawal(long accountNumber,double amount) {
		return transactionDao.withdrawal(accountNumber, amount);
	}

	public double showBalance(long accountNumber) {
		return transactionDao.showBalance(accountNumber);
	}

	public int fundTransfer(TransactionDetails transactionDetails) {
		return transactionDao.fundTransfer(transactionDetails);
	}

}
