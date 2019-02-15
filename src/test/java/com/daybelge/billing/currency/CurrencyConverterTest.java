package com.daybelge.billing.currency;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.daybelge.billing.currency.StaticCurrencyConverter;
import com.daybelge.billing.currency.MoneyAmount;
import com.daybelge.billing.currency.MoneyAmountConversionException;

public class CurrencyConverterTest {
	
	private static final Currency TRY = Currency.getInstance("TRY");
	private static final Currency GBP = Currency.getInstance("GBP");
	private static final Currency USD = Currency.getInstance(Locale.US);

	@Test
	public void convertTRYtoUSD() {
		MoneyAmount moneyAmount = new MoneyAmount(TRY, BigDecimal.valueOf(7));
		MoneyAmount convertedAmount = new StaticCurrencyConverter().convert(moneyAmount, USD);
	
		assertEquals("conversion currency incorrect.", USD, convertedAmount.getCurrency());
		assertEquals("conversion amount incorrect.", BigDecimal.valueOf(1.33), convertedAmount.getAmount());
	}
	
	@Test
	public void convertUSDtoTRY() {
		MoneyAmount moneyAmount = new MoneyAmount(USD, BigDecimal.valueOf(7));
		MoneyAmount convertedAmount = new StaticCurrencyConverter().convert(moneyAmount, TRY);
	
		assertEquals("conversion currency incorrect.", TRY, convertedAmount.getCurrency());
		assertEquals("conversion amount incorrect.", BigDecimal.valueOf(37.03), convertedAmount.getAmount());
	}
	
	@Test(expected=MoneyAmountConversionException.class)
	public void convertUndefinedCurrency() {
		MoneyAmount moneyAmount = new MoneyAmount(USD, BigDecimal.valueOf(7));
		//Convert to undefined currency
		new StaticCurrencyConverter().convert(moneyAmount, GBP);
	}
}
