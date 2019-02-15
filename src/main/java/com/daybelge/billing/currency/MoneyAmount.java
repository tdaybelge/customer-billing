package com.daybelge.billing.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MoneyAmount {
	private final Currency currency;
	private final BigDecimal amount;
	private NumberFormat formatter;
	
	public static MoneyAmount dollar(BigDecimal amount) {
		return new MoneyAmount(Currency.getInstance(Locale.US), amount);
	}
	
	public static MoneyAmount dollar(int amount) {
		return new MoneyAmount(Currency.getInstance(Locale.US), BigDecimal.valueOf(amount));
	}
	
	public static MoneyAmount dollar(String amount) {
		return new MoneyAmount(Currency.getInstance(Locale.US), new BigDecimal(amount));
	}
	
	public MoneyAmount(Currency currency, BigDecimal amount) {
		if(currency == null) {
			throw new IllegalArgumentException("Money currency cannot be null.");
		}
		if(amount == null) {
			throw new IllegalArgumentException("Money amount cannot be null.");
		}
		this.currency = currency;
		this.amount = amount.setScale(2, RoundingMode.HALF_UP);
		this.formatter = initFormatter(currency);
	}
	
	private static NumberFormat initFormatter(Currency currency) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.setCurrency(currency);
		return formatter;
	}

	public String toString() {
		return formatter.format(amount.doubleValue());
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount.hashCode();
		result = prime * result + currency.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoneyAmount other = (MoneyAmount) obj;
		if (amount.compareTo(other.amount) != 0)
			return false;
		return currency.equals(other.currency);
	}
}
