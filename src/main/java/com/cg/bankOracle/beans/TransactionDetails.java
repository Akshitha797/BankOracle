package com.cg.bankOracle.beans;

public class TransactionDetails {
	
	private long fromAccountNo;
	private long toAccountNo;
	private double amountTransfered;
	
	public long getFromAccountNo() {
		return fromAccountNo;
	}
	public void setFromAccountNo(long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}
	public long getToAccountNo() {
		return toAccountNo;
	}
	public void setToAccountNo(long toAccountNo) {
		this.toAccountNo = toAccountNo;
	}
	public double getAmountTransfered() {
		return amountTransfered;
	}
	public void setAmountTransfered(double amountTransfered) {
		this.amountTransfered = amountTransfered;
	}
}
