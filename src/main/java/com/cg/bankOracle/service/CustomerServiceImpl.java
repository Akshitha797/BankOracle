package com.cg.bankOracle.service;

import com.cg.bankOracle.beans.CustomerDetails;
import com.cg.bankOracle.dao.CustomerDao;
import com.cg.bankOracle.dao.CustomerDaoImpl;

public class CustomerServiceImpl implements CustomerService{

	CustomerDao customerDao=new CustomerDaoImpl();

	
	public long registration(CustomerDetails customerDetails) {
		return customerDao.registration(customerDetails);
	}

	public boolean Login(Long accountNumber,String password) {
		
		return customerDao.Login(accountNumber, password);
	}
	public boolean validateMobileNo(long mobileNo) {
		String mobile=String.valueOf(mobileNo);
		if(mobile.length()==10)
			return true;
		else 
			return false;
	}
	
	public boolean validateAadhar(long aadharCardNo) {
		String aadhar=String.valueOf(aadharCardNo);
		if(aadhar.length() == 12)
			return true;
		else
			return false;
	}

}
