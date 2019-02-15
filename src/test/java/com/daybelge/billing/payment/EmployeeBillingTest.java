package com.daybelge.billing.payment;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.daybelge.billing.currency.MoneyAmount;
import com.daybelge.billing.product.Product;

public class EmployeeBillingTest extends PaymentTestBase {

	private static BillItem groceries;
	private static BillItem clothes;
	private static BillItem books;
	
	@BeforeClass
	public static void initTests() {
		
		Product grocery = Product.grocery("apple", MoneyAmount.dollar(3));
		groceries = new BillItem(grocery, 2);
		
		Product cloth = Product.cloth("t-shirt", MoneyAmount.dollar(10));
		clothes = new BillItem(cloth, 3);
		
		Product book = Product.book("Alice in Wonderland", MoneyAmount.dollar(15));
		books = new BillItem(book, 20);
	}
	
	
	@Test
	public void calculatePayableAmountBelow100() {
			
		Bill bill = BillBuilder.forUser(bob).addItem(groceries).addItem(clothes).setCurrencyConverter(converter).build();
		MoneyAmount payableAmount = bill.calculatePayableAmount();
		
		assertEquals("Payable amount incorrect.", MoneyAmount.dollar(27), payableAmount);
	}
	
	@Test
	public void calculatePayableAmountOver100() {
		
		Bill bill = BillBuilder.forUser(bob).addItem(groceries).addItem(clothes).addItem(books).setCurrencyConverter(converter).build();
		MoneyAmount payableAmount = bill.calculatePayableAmount();
		
		assertEquals("Payable amount incorrect.", MoneyAmount.dollar(227), payableAmount);
	}
}
