package com.revature.pojos;

/**
 * 
 * @author thienle
 *
 */
public class AccountType {
	
	private int typeId;
	private String accountType;
	
	public AccountType() {}

	public AccountType(int typeId, String accountType) {
		super();
		this.typeId = typeId;
		this.accountType = accountType;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "AccountType [typeId=" + typeId + ", accountType=" + accountType + "]";
	}
	
	

}
