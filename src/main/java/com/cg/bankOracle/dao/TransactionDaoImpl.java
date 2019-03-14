package com.cg.bankOracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.bankOracle.beans.CustomerDetails;
import com.cg.bankOracle.beans.TransactionDetails;
import com.cg.bankOracle.utility.Database;

public class TransactionDaoImpl implements TransactionDao{
	
	Database database=new Database();
	CustomerDetails customerDetails=new CustomerDetails();

	public CustomerDetails deposit(long accountNumber,double amount) {
		
		Connection connection=database.database();
		double balance=0;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("select balance from customer_details where account_no=?");
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				balance=resultSet.getDouble(1);
			}
			
			PreparedStatement preparedStatement1=connection.prepareStatement("update customer_details set balance = ? where account_no=?");
			balance=balance+amount;
			preparedStatement1.setDouble(1,balance);
			preparedStatement1.setLong(2, accountNumber);
			
			preparedStatement1.executeUpdate();
			
			customerDetails.setBalance(balance);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerDetails;
	}

	public CustomerDetails withdrawal(long accountNumber,double amount) {
		Connection connection=database.database();
		double balance=0;
		int count=0;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("select * from customer_details where account_no=?");
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				balance=resultSet.getDouble(10);
			}
			if(balance>amount) {
			PreparedStatement preparedStatement1=connection.prepareStatement("update customer_details set balance = ?");
			balance=balance-amount;
			
			preparedStatement1.setDouble(1,balance);
			
			preparedStatement1.executeUpdate();
			
			customerDetails.setBalance(balance);
			count++;
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count==0)
			return null;	
		else
			return customerDetails;
		}

	public CustomerDetails showBalance(long accountNumber) {
		
		Connection connection=database.database();
		double balance=0;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("select * from customer_details where account_no=?");
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				balance=resultSet.getDouble(10);
			}
			
			customerDetails.setBalance(balance);
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerDetails;
	
	}

	public int fundTransfer(TransactionDetails transactionDetails) {
		
		Connection connection=database.database();
		double balance=0;
		int transactionId=0;
		int c1=0,c2=0;
		
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from customer_details");
			
			while(resultSet.next()) {
				if(transactionDetails.getToAccountNo()==resultSet.getLong(1)) {
					c1++;
					PreparedStatement preparedStatement=connection.prepareStatement("select * from customer_details where account_no=?");
					preparedStatement.setLong(1, transactionDetails.getFromAccountNo());
					ResultSet resultSet1=preparedStatement.executeQuery();
					
					while(resultSet1.next()) {
						 balance=resultSet1.getDouble(10);
					}
					
					if(balance > transactionDetails.getAmountTransfered()) {
						
						c2++;
						PreparedStatement preparedStatement1=connection.prepareStatement("update customer_details set balance=? where account_no=?");
						balance=balance-transactionDetails.getAmountTransfered();
						preparedStatement1.setDouble(1, balance);
						preparedStatement1.setLong(2, transactionDetails.getFromAccountNo());
						
						preparedStatement1.executeUpdate();
						
						
						
						PreparedStatement preparedStatement2=connection.prepareStatement("select * from customer_details where account_no=?");
						preparedStatement2.setLong(1, transactionDetails.getToAccountNo());
						ResultSet resultSet2=preparedStatement2.executeQuery();
						
						while(resultSet2.next()) {
							 balance=resultSet2.getDouble(10);
						}
							
		
						
						PreparedStatement preparedStatement3=connection.prepareStatement("update customer_details set balance=? where account_no=?");
						balance=balance+transactionDetails.getAmountTransfered();
						preparedStatement3.setDouble(1, balance);
						preparedStatement3.setLong(2, transactionDetails.getToAccountNo());
						
						preparedStatement3.executeUpdate();
												
					
						PreparedStatement preparedStatement4=connection.prepareStatement("insert into transaction_details values(transaction_id_seq.nextval,?,?,?)");
						preparedStatement4.setLong(1,transactionDetails.getFromAccountNo());
						preparedStatement4.setLong(2, transactionDetails.getToAccountNo());
						preparedStatement4.setDouble(3, transactionDetails.getAmountTransfered());
					
						preparedStatement4.executeUpdate();
					
										
					
						PreparedStatement preparedStatement5=connection.prepareStatement("select * from transaction_details where to_account_no=?");
						preparedStatement5.setLong(1, transactionDetails.getToAccountNo());
						ResultSet resultSet3=preparedStatement5.executeQuery();
					
						while(resultSet3.next()) {
							if(resultSet3.getInt(1)>transactionId)
								transactionId=resultSet3.getInt(1);
						}	
						break;
					}
					
					
					
				}
			}
			
			connection.close();					

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(c1==0)
			return 0;
		else if(c2==0)
			return 1;
		else
			return transactionId;
	}

}
