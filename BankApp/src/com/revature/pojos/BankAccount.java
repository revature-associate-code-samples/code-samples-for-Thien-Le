package com.revature.pojos;

public class BankAccount {
	
	private int accountId;
	private int typeId;
	private int userId;
	private double balance;
	
	public BankAccount() {}
	
	public BankAccount(int accountId, int typeId, int userId, double balance) {
		super();
		this.accountId = accountId;
		this.typeId = typeId;
		this.userId = userId;
		this.balance = balance;
	}
	
	
	public void withdraw(Double amount) throws InsufficientFundsException {
		
		if(amount <= balance) {
			balance -= amount;
		}
		else {
			double needs = amount - balance;
			throw new InsufficientFundsException(needs);
		}
	
		
	}
	
	public void deposit(Double amount) {
		balance += amount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", typeId=" + typeId + ", userId=" + userId + ", balance="
				+ balance + "]";
	}
	
	

}
