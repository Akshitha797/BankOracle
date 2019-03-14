package com.cg.bankOracle.beans;

public class CustomerDetails {

	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private long pancardNumber;
	private long aadharCardNumber;
	private String address;
	private long mobileNo;
	private double balance;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getPancardNumber() {
		return pancardNumber;
	}
	public void setPancardNumber(long panCardNumber) {
		this.pancardNumber = panCardNumber;
	}
	public long getAadharCardNumber() {
		return aadharCardNumber;
	}
	public void setAadharCardNumber(long aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}	
	
}
