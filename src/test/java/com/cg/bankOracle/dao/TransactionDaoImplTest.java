package com.cg.bankOracle.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.bankOracle.beans.CustomerDetails;
import com.cg.bankOracle.beans.TransactionDetails;

class TransactionDaoImplTest {

	static CustomerDetails customer;
	static TransactionDetails transaction;
	static TransactionDao transactionDao;
	
	
	@BeforeAll
	public static void init() {
		customer=new CustomerDetails();
		transaction=new TransactionDetails();
		transactionDao=new TransactionDaoImpl();
	}
	
	@Test
	void testDeposit() {
		
		customer=transactionDao.deposit(100002, 5000);
		assertEquals(15000,customer.getBalance());
	}

	@Test
	void testWithdrawal() {
		customer=transactionDao.withdrawal(100002, 3000);
		assertEquals(12000,customer.getBalance());
	}

	@Test
	void testShowBalance() {
		customer=transactionDao.showBalance(100002);
		assertEquals(10000,customer.getBalance());
	}

	@Test
	void testFundTransfer() {
		transaction.setFromAccountNo(100002);
		transaction.setToAccountNo(100003);
		transaction.setAmountTransfered(2000);
		assertEquals(122,transactionDao.fundTransfer(transaction));
		
	}

}
