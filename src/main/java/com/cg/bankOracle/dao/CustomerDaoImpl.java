package com.cg.bankOracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.bankOracle.beans.CustomerDetails;
import com.cg.bankOracle.utility.Database;

public class CustomerDaoImpl implements CustomerDao{
	
	Database database=new Database();

	public long registration(CustomerDetails customerDetails) {
		
		Connection connection=database.database();
		long accountNumber=0;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("insert into customer_details values(ACCOUNT_NO_SEQ.nextval,?,?,?,?,?,?,?,?,?)");
			
			preparedStatement.setString(1,customerDetails.getFirstName());
			preparedStatement.setString(2, customerDetails.getLastName());
			preparedStatement.setString(3, customerDetails.getEmailId());
			preparedStatement.setString(4, customerDetails.getPassword());
			preparedStatement.setLong(5, customerDetails.getPancardNumber());
			preparedStatement.setLong(6, customerDetails.getAadharCardNumber());
			preparedStatement.setString(7, customerDetails.getAddress());
			preparedStatement.setLong(8, customerDetails.getMobileNo());
			preparedStatement.setDouble(9, customerDetails.getBalance());
			
			preparedStatement.executeUpdate();
			
			PreparedStatement preparedStatement2=connection.prepareStatement("select * from customer_details where aadhar_no=?");
			
			preparedStatement2.setLong(1, customerDetails.getAadharCardNumber());
			
			ResultSet resultSet=preparedStatement2.executeQuery();
			
			while(resultSet.next()) {
			accountNumber=resultSet.getLong(1);
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountNumber;
	}

	public boolean Login(long accountNumber, String password) {

		Connection connection=database.database();
		int count=0;
		try {
			Statement statement=connection.createStatement();			
			ResultSet resultSet=statement.executeQuery("select * from customer_details");
			while(resultSet.next()){
				if(accountNumber==resultSet.getLong(1) && password.equals(resultSet.getString(5))) {
					count++;
					break;
				}
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(count==0)
			return false;
		else
			return true;
	}

}
