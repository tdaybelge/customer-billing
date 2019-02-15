package com.daybelge.billing.user;

public abstract class User {
	private String name;
	private String surname;
	private String address;
	
	public abstract int getDiscountRate();

	public User(String name, String surname, String address) {
		setName(name);
		setSurname(surname);
		setAddress(address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("User name cannot be null.");
		}
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if(surname == null) {
			throw new IllegalArgumentException("User surname cannot be null.");
		}
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(address == null) {
			throw new IllegalArgumentException("User address cannot be null.");
		}
		this.address = address;
	}

	@Override
	public String toString() {
		return "name=" + getName() + " " + getSurname() + ", address=" + getAddress();
	}
}
