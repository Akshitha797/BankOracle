package com.cg.bankOracle.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.bankOracle.beans.CustomerDetails;

class CustomerDaoImplTest {

	static CustomerDetails customer;
	static CustomerDao customerDao;
	
	
	@BeforeAll
	public static void init() {
		customer=new CustomerDetails();
		customerDao=new CustomerDaoImpl();
	}
	
	@Test
	void testRegistration() {

		customer.setFirstName("Bob");
		customer.setLastName("Marley");
		customer.setEmailId("Bob@gmail.com");
		customer.setPassword("1234");
		customer.setPancardNumber(456321456123L);
		customer.setAadharCardNumber(645213456987L);
		customer.setAddress("Hyderabad");
		customer.setMobileNo(9874563214L);
		customer.setBalance(0);
		
		assertEquals(100005,customerDao.registration(customer));
		
	}

	@Test
	void testLogin() {
		assertEquals(true,customerDao.Login(100002, "akshitha123"));
	}

}
