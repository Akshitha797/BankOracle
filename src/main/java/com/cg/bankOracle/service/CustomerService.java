package com.cg.bankOracle.service;

import com.cg.bankOracle.beans.CustomerDetails;

public interface CustomerService {
		
	public long registration(CustomerDetails customerDetails);
	public boolean Login(Long accountNumber,String password);

}
