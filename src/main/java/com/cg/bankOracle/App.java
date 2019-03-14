package com.cg.bankOracle;

import java.util.Scanner;

import com.cg.bankOracle.beans.CustomerDetails;
import com.cg.bankOracle.beans.TransactionDetails;
import com.cg.bankOracle.service.CustomerService;
import com.cg.bankOracle.service.CustomerServiceImpl;
import com.cg.bankOracle.service.TransactionService;
import com.cg.bankOracle.service.TransactionServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner s;
	static CustomerDetails customerDetails=new CustomerDetails();
	static CustomerService customerService=new CustomerServiceImpl();
	static TransactionDetails transactionDetails=new TransactionDetails();
	static TransactionService transactionService=new TransactionServiceImpl();
	
    public static void main( String[] args )
    {
    	s=new Scanner(System.in);
    	while(true) {
    	System.out.println("======Welcome to Bank======");
    	System.out.println("Enter your choice : ");
    	System.out.println("1.Register \n 2.Login \n 3.Exit");
    	int ch=s.nextInt();
    	
    	switch(ch) {
    	case 1 : System.out.println("Enter First Name: ");
    	customerDetails.setFirstName(s.next());
    	System.out.println("Enter Last Name: ");
    	customerDetails.setLastName(s.next());
    	System.out.println("Enter Email Id:");
    	customerDetails.setEmailId(s.next());
    	System.out.println("Enter password: ");
    	customerDetails.setPassword(s.next());
    	System.out.println("Enter PanCard Number: ");
    	customerDetails.setPancardNumber(s.nextLong());
    	System.out.println("Enter Aadhar Card Number: ");
    	customerDetails.setAadharCardNumber(s.nextLong());
    	System.out.println("Enter Address: ");
    	customerDetails.setAddress(s.next());
    	System.out.println("Enter Mobile No :");
    	customerDetails.setMobileNo(s.nextLong());
    	customerDetails.setBalance(0);
    	
    	long accountNumber=customerService.registration(customerDetails);
    	
    	System.out.println("Successfully Registered ! Your Account Number is "+accountNumber);
    		break;
    		
    	case 2 : System.out.println("Enter Account Number: ");
    	long accountNumber1=s.nextLong();
    	System.out.println("Enter password: ");
    	String password=s.next();
    	if(customerService.Login(accountNumber1, password)) {
			System.out.println("Login Successfull !!");
			int choice=0;
    		while(choice!=1) {
    			System.out.println("Enter your choice: ");
    			System.out.println("1.Deposit \n 2.Withdrawal \n 3.Show Balance \n 4.Fund Transfer \n 5.Return to main menu \n 6.Exit");
    			
    			int ch1=s.nextInt();
    			
    			double amount=0,balance=0;
    			
    			switch(ch1) {
    			case 1 : System.out.println("Enter the amount: ");
    			amount=s.nextDouble();
    			balance=transactionService.deposit(accountNumber1, amount);
    			System.out.println("Your balance is "+balance);
    			
    				break;
    				
    			case 2 : System.out.println("Enter the amount: ");
    			amount=s.nextDouble();
    			balance=transactionService.withdrawal(accountNumber1, amount);
    			System.out.println("Your balance is "+balance);
    				break;
    			
    			case 3 : balance=transactionService.showBalance(accountNumber1);
    			System.out.println("Your balance is "+balance);
    				break;
    				
    			case 4 : System.out.println("Enter the account number to which you want to transfer: ");
    			transactionDetails.setToAccountNo(s.nextLong());
    			System.out.println("Enter the amount to be transfered: ");
    			transactionDetails.setAmountTransfered(s.nextDouble());
    			transactionDetails.setFromAccountNo(accountNumber1);
    			
    			System.out.println("Transfered successfully ! Your transaction id is "+transactionService.fundTransfer(transactionDetails));
    			
    				break;
    				
    			case 5 : choice++;
    				break;
    				
    			case 6 : System.exit(0);
    				
    			default : System.out.println("Wrong Choice !!");
    			}
    		}
    	}
    	else
    		System.out.println("Wrong account number or password");
    		break;
    		
    	case 3 : System.exit(0);
    	
    	default : System.err.println("Wrong choice !!");
    	}
    	}
    }
}
