package com.nishant.DevChallengePrj.model;

public class ShopAddress {

	private String number;
	private String postCode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public ShopAddress(String number, String postCode) {
		super();
		this.number = number;
		this.postCode = postCode;
	}
	
	public ShopAddress() {
	}
	
	@Override
	public String toString() {
		return String.format(
				"ShopAddress [number=%s, postCode=%s]", number,
				postCode);
	}
}
