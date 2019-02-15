package com.daybelge.billing.payment;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

import com.daybelge.billing.currency.MoneyAmount;
import com.daybelge.billing.product.Product;

public class BillItemTest {

	@Test
	public void calculateGroceryPayableAmount() {

		Product grocery = Product.grocery("apple", MoneyAmount.dollar(3));
		BillItem item = new BillItem(grocery, 2);

		MoneyAmount amount = item.calculatePayableAmount();
		assertEquals("Grocery must have no discount.", MoneyAmount.dollar(6), amount);
	}

	@Test
	public void calculateClothingPayableAmount() {

		Product cloth = Product.cloth("t-shirt", MoneyAmount.dollar(10));
		BillItem item = new BillItem(cloth, 3);

		MoneyAmount amount = item.calculatePayableAmount();
		assertEquals("Clothing must have percentage discount.", MoneyAmount.dollar("30.00"), amount);
	}

	@Test
	public void calculateBookPayableAmount() {

		Product book = Product.book("Alice in Wonderland", new MoneyAmount(Currency.getInstance("TRY"), new BigDecimal("52.90")));
		BillItem item = new BillItem(book, 1);

		MoneyAmount amount = item.calculatePayableAmount();
		assertEquals("Clothing must have percentage discount.",
				new MoneyAmount(Currency.getInstance("TRY"), new BigDecimal("52.90")), amount);
	}

	@Test
	public void stringConvert() {

		Product book = Product.book("Alice in Wonderland", MoneyAmount.dollar(10));
		BillItem item = new BillItem(book, 2);
		assertEquals("string value incorrect.", "Alice in Wonderland x2\t\t$10.00", item.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullProduct() {
		new BillItem(null, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullCount() {
		Product book = Product.book("Alice in Wonderland", MoneyAmount.dollar(10));
		new BillItem(book, -1);
	}
}
