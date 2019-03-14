package com.cg.bankOracle.dao;

import com.cg.bankOracle.beans.CustomerDetails;

public interface CustomerDao {

	public long registration(CustomerDetails customerDetails);
	public boolean Login(long accountNumber,String password);

}
