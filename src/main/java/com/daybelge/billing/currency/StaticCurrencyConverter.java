package com.daybelge.billing.currency;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class StaticCurrencyConverter implements CurrencyConverter {
	
	private static Map<String, BigDecimal> parityTable = new HashMap<>();
	
	static {
		parityTable.put("USD/TRY", new BigDecimal("5.29"));
		parityTable.put("TRY/USD", new BigDecimal("0.19"));
	}
	
	public MoneyAmount convert(MoneyAmount moneyAmount, Currency currency) {
		//nothing to convert
		if(currency.equals(moneyAmount.getCurrency())) {
			return moneyAmount;
		}
		String parityCode = moneyAmount.getCurrency().getCurrencyCode() + "/" + currency.getCurrencyCode();
		BigDecimal ratio = parityTable.get(parityCode);
		if(ratio == null) {
			throw new MoneyAmountConversionException();
		}
		
		return new MoneyAmount(currency, ratio.multiply(moneyAmount.getAmount()));
	}

}
