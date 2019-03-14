package com.cg.bankOracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.bankOracle.beans.TransactionDetails;
import com.cg.bankOracle.utility.Database;

public class TransactionDaoImpl implements TransactionDao{
	
	Database database=new Database();

	public double deposit(long accountNumber,double amount) {
		
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
			
			int i=preparedStatement1.executeUpdate();
			
			if(i==1)
				System.out.println("Successfull");
			else
				System.out.println("Error!");
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	public double withdrawal(long accountNumber,double amount) {
		Connection connection=database.database();
		double balance=0;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("select * from customer_details where account_no=?");
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				balance=resultSet.getDouble(10);
			}
			PreparedStatement preparedStatement1=connection.prepareStatement("update customer_details set balance = ?");
			if(balance>amount) 
			balance=balance-amount;
			
			preparedStatement1.setDouble(1,balance);
			
			preparedStatement1.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;	
		}

	public double showBalance(long accountNumber) {
		
		Connection connection=database.database();
		double balance=0;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("select * from customer_details where account_no=?");
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				balance=resultSet.getDouble(10);
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	
	}

	public int fundTransfer(TransactionDetails transactionDetails) {
		
		Connection connection=database.database();
		double balance=0;
		int transactionId=0;
		
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from customer_details");
			
			while(resultSet.next()) {
				if(transactionDetails.getToAccountNo()==resultSet.getLong(1)) {
					
					PreparedStatement preparedStatement=connection.prepareStatement("insert into transaction_details values(transaction_id_seq.nextval,?,?,?)");
					preparedStatement.setLong(1,transactionDetails.getFromAccountNo());
					preparedStatement.setLong(2, transactionDetails.getToAccountNo());
					preparedStatement.setDouble(3, transactionDetails.getAmountTransfered());
					
					preparedStatement.executeUpdate();
					
					
					PreparedStatement preparedStatement1=connection.prepareStatement("select * from transaction_details where to_account_no=?");
					preparedStatement1.setLong(1, transactionDetails.getToAccountNo());
					//preparedStatement1.setLong(2, transactionDetails.getFromAccountNo());
					//preparedStatement1.setDouble(3, transactionDetails.getAmountTransfered());
					ResultSet resultSet1=preparedStatement1.executeQuery();
					
					while(resultSet1.next()) {
						if(resultSet1.getInt(1)>transactionId)
						transactionId=resultSet1.getInt(1);
					}
					
					
					PreparedStatement preparedStatement2=connection.prepareStatement("select * from customer_details where account_no=?");
					preparedStatement2.setLong(1, transactionDetails.getFromAccountNo());
					ResultSet resultSet2=preparedStatement2.executeQuery();
					
					while(resultSet2.next()) {
						 balance=resultSet2.getDouble(10);
					}
					
					
					
					PreparedStatement preparedStatement3=connection.prepareStatement("update customer_details set balance=? where account_no=?");
					if(balance>=transactionDetails.getAmountTransfered()) {
					balance=balance-transactionDetails.getAmountTransfered();
					preparedStatement3.setDouble(1, balance);
					preparedStatement3.setLong(2, transactionDetails.getFromAccountNo());
					
					preparedStatement3.executeUpdate();
					}
					
					
					PreparedStatement preparedStatement4=connection.prepareStatement("select * from customer_details where account_no=?");
					preparedStatement4.setLong(1, transactionDetails.getToAccountNo());
					ResultSet resultSet3=preparedStatement4.executeQuery();
					
					while(resultSet3.next()) {
						 balance=resultSet3.getDouble(10);
					}
						
	
					
					PreparedStatement preparedStatement5=connection.prepareStatement("update customer_details set balance=? where account_no=?");
					balance=balance+transactionDetails.getAmountTransfered();
					preparedStatement5.setDouble(1, balance);
					preparedStatement5.setLong(2, transactionDetails.getToAccountNo());
					
					preparedStatement5.executeUpdate();
					
					
				}
			}
			
			connection.close();					

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionId;
	}

}
