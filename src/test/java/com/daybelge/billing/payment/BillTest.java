package com.daybelge.billing.payment;

import static org.junit.Assert.assertEquals;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.daybelge.billing.currency.MoneyAmount;
import com.daybelge.billing.product.Product;

public class BillTest extends PaymentTestBase {
	
	@Test
	public void stringConvert() {

		Product grocery = Product.grocery("apple", MoneyAmount.dollar(3));
		BillItem item1 = new BillItem(grocery, 2);

		Product cloth = Product.cloth("t-shirt", MoneyAmount.dollar(10));
		BillItem item2 = new BillItem(cloth, 3);

		Bill bill = BillBuilder.forUser(bob).addItem(item1).addItem(item2).setCurrency(Currency.getInstance(Locale.US))
				.setCurrencyConverter(converter).build();

		assertEquals("string value incorrect.", bob + "\n" + item1 + "\n" + item2 + "\n", bill.toString());
	}
}
