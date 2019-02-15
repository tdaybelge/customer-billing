package com.daybelge.billing.user;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Customer extends User {

	private ZonedDateTime membershipStart;
	
	public Customer(String name, String surname, String address, ZonedDateTime membershipStart) {
		super(name, surname, address);
		setMembershipStart(membershipStart);
	}
	
	public ZonedDateTime getMembershipStart() {
		return membershipStart;
	}

	public void setMembershipStart(ZonedDateTime membershipStart) {
		if(membershipStart == null) {
			throw new IllegalArgumentException("membership start date cannot be null.");
		}
		this.membershipStart = membershipStart;
	}

	@Override
	public int getDiscountRate() {
		long years = ChronoUnit.YEARS.between(getMembershipStart(), ZonedDateTime.now());
		if(years >= 2) {
			return 5;
		} else {
			return 0;
		}
	}
}
