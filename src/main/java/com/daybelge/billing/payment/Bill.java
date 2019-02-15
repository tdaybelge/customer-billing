package com.daybelge.billing.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import com.daybelge.billing.currency.CurrencyConverter;
import com.daybelge.billing.currency.MoneyAmount;
import com.daybelge.billing.user.User;

public class Bill implements Payable {

	private User user;
	private BillItem[] items;
	private Currency currency;
	private CurrencyConverter converter;
	
	public Bill(User user, BillItem[] items, Currency currency, CurrencyConverter converter) {
		setUser(user);
		setItems(items);
		setCurrency(currency);
		setConverter(converter);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BillItem[] getItems() {
		return items;
	}

	public void setItems(BillItem[] items) {
		this.items = items;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public CurrencyConverter getConverter() {
		return converter;
	}

	public void setConverter(CurrencyConverter converter) {
		this.converter = converter;
	}

	public MoneyAmount calculatePayableAmount() {

		BigDecimal total = BigDecimal.ZERO;
		BigDecimal subTotal;
		for (BillItem item : items) {
			MoneyAmount itemAmount = item.calculatePayableAmount();
			subTotal = converter.convert(itemAmount, getCurrency()).getAmount();
			if(item.isEligibleForDiscount()) {
				subTotal = calculateDiscount(subTotal, user.getDiscountRate());
			}
			total = total.add(subTotal);
		}
		
		// apply $5 discount for every $100 here
		total = total.subtract(total.divide(new BigDecimal(100)).setScale(0, RoundingMode.FLOOR).multiply(new BigDecimal(5)));
		return new MoneyAmount(getCurrency(), total);
	}
	
	private BigDecimal calculateDiscount(BigDecimal amount, int rate) {
		return amount.multiply(BigDecimal.valueOf((double) (100 - rate) / 100));
	}
	
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append(getUser().toString());
		bld.append('\n');
		for(BillItem item : getItems()) {
			bld.append(item);
			bld.append('\n');
		}
		return bld.toString();
	}
}
