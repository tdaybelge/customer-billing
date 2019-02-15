package com.daybelge.billing.user;

public class Employee extends User {
	
	private String employeeId;
	
	public Employee(String name, String surname, String address, String employeeId) {
		super(name, surname, address);
		setEmployeeId(employeeId);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		if(employeeId == null) {
			throw new IllegalArgumentException("Employee id cannot be null.");
		}
		this.employeeId = employeeId;
	}

	@Override
	public int getDiscountRate() {
		return 30;
	}

	@Override
	public String toString() {
		return "employeeId=" + getEmployeeId() + ", " + super.toString();
	}
}
