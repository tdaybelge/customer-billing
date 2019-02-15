package com.daybelge.billing.payment;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import com.daybelge.billing.currency.CurrencyConverter;
import com.daybelge.billing.currency.StaticCurrencyConverter;
import com.daybelge.billing.user.User;

public class BillBuilder {
	
	private User user;
	private List<BillItem> items;
	private Currency currency;
	private CurrencyConverter currencyConverter;
	
	private BillBuilder(User user) {
		this.user = user;
		items = new ArrayList<>();
	}
	
	public Bill build() {
		//Default currency is USD
		if(currency == null) {
			currency = Currency.getInstance(Locale.US);
		}
		//Default currency converter
		if(currencyConverter == null) {
			currencyConverter = new StaticCurrencyConverter();
		}
		return new Bill(user, items.toArray(new BillItem[items.size()]), currency, currencyConverter);
	}

	public static BillBuilder forUser(User user) {
		return new BillBuilder(user);
	}

	public BillBuilder addItem(BillItem item) {
		items.add(item);
		return this;
	}
	
	public BillBuilder setCurrency(Currency currency) {
		this.currency = currency;
		return this;
	}
	
	public BillBuilder setCurrencyConverter(CurrencyConverter currencyConverter) {
		this.currencyConverter = currencyConverter;
		return this;
	}
}
