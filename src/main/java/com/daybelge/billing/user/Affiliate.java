package com.daybelge.billing.user;

public class Affiliate extends User {

	public Affiliate(String name, String surname, String address) {
		super(name, surname, address);
	}

	@Override
	public int getDiscountRate() {
		return 10;
	}
	
}
