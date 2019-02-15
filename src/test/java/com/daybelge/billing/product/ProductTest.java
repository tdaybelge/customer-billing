package com.daybelge.billing.product;

import org.junit.Test;

import com.daybelge.billing.currency.MoneyAmount;

public class ProductTest {

	@Test(expected=IllegalArgumentException.class)
	public void constructorNullName() {
		new Product(null, ProductType.GROCERY, MoneyAmount.dollar(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullType() {
		new Product("", null, MoneyAmount.dollar(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullAmount() {
		new Product("", ProductType.GROCERY, null);
	}
}
