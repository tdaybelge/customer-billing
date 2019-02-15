package com.daybelge.billing.user;

import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;

import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTest {

	private static User alice;
	private static User bob;
	private static User justin;
	
	@BeforeClass
	public static void init() {
		alice = new Customer("Alice", "Cooper", "Address 2", ZonedDateTime.now().minusYears(2));
		bob = new Employee("Bob", "Ross", "Address 3", "br97123");
		justin = new Customer("Justin", "Bieber", "Address 1", ZonedDateTime.now().minusDays(10));
	}
	
	@Test
	public void newCustomerDiscount() {
		//Customer since 10 days
		assertEquals("New customer must get no discount.", 0, justin.getDiscountRate());
	}
	
	@Test
	public void oldCustomerDiscount() {
		//Customer since 2 years
		assertEquals("Old customer must get 5% discount.", 5, alice.getDiscountRate());
	}
	
	@Test
	public void stringConvertCustomer() {
		assertEquals("string value incorrect", "name=Alice Cooper, address=Address 2", alice.toString());
	}
	
	@Test
	public void stringConvertEmployee() {
		assertEquals("string value incorrect", "employeeId=br97123, name=Bob Ross, address=Address 3", bob.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorUserNameNull() {
		new Customer(null, "", "", ZonedDateTime.now());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorUserSurnameNull() {
		new Customer("", null, "", ZonedDateTime.now());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorAddressNull() {
		new Customer("", "", null, ZonedDateTime.now());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorMembershipStartNull() {
		new Customer("", "", "", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorEmployeeIdNull() {
		new Employee("", "", "", null);
	}
}
