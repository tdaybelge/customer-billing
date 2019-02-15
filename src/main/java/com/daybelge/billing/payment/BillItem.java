package com.daybelge.billing.payment;

import java.math.BigDecimal;

import com.daybelge.billing.currency.MoneyAmount;
import com.daybelge.billing.product.Product;
import com.daybelge.billing.product.ProductType;

public class BillItem implements Payable {
	
	private Product product;
	private int count;
	
	public BillItem(Product product, int count) {
		setProduct(product);
		setCount(count);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if(product == null) {
			throw new IllegalArgumentException("product cannot be null.");
		}
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if(count <= 0) {
			throw new IllegalArgumentException("count must be positive.");
		}
		this.count = count;
	}

	public String toString() {
		return getProduct().getName() + " x" + getCount() + "\t\t" + product.getPrice();
	}

	@Override
	public MoneyAmount calculatePayableAmount() {
		MoneyAmount price = product.getPrice();
		BigDecimal payableAmount = price.getAmount().multiply(BigDecimal.valueOf(getCount()));
		return new MoneyAmount(price.getCurrency(), payableAmount);
	}

	public boolean isEligibleForDiscount() {
		return !ProductType.GROCERY.equals(product.getType());
	}
}
