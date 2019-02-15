package com.daybelge.billing.currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

public class MoneyAmountTest {

	@Test(expected=IllegalArgumentException.class)
	public void nullCurrency() {
		new MoneyAmount(null, BigDecimal.ONE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullAmount() {
		new MoneyAmount(Currency.getInstance(Locale.US), null);
	}
	
	@Test
	public void testDollarFormat() {
		BigDecimal amount = new BigDecimal("100.15");
		MoneyAmount dollar = MoneyAmount.dollar(amount);
		assertEquals("formatted amount incorrect.", "$100.15", dollar.toString());
	}

	@Test
	public void testDollarProperties() {
		BigDecimal amount = new BigDecimal("100.15");
		MoneyAmount dollar = MoneyAmount.dollar(amount);
		assertEquals("currency incorrect.", Currency.getInstance(Locale.US), dollar.getCurrency());
		assertEquals("amount incorrect.", amount, dollar.getAmount());
	}

	@Test
	public void currencyNotEquals() {
		BigDecimal amount = new BigDecimal("100.15");
		MoneyAmount dollar = MoneyAmount.dollar(amount);
		MoneyAmount turkish = new MoneyAmount(Currency.getInstance("TRY"), amount);

		assertNotEquals("currencies are the same.", dollar, turkish);
	}
	
	@Test
	public void amountNotEquals() {
		BigDecimal amount1 = new BigDecimal("100.15");
		MoneyAmount dollar1 = MoneyAmount.dollar(amount1);
		
		BigDecimal amount2 = new BigDecimal("10.33");
		MoneyAmount dollar2 = MoneyAmount.dollar(amount2);
	
		assertNotEquals("amount are the same.", dollar1, dollar2);
	}
	
	@Test
	public void notEquals() {
		BigDecimal amount1 = new BigDecimal("100.15");
		MoneyAmount dollar1 = MoneyAmount.dollar(amount1);
	
		assertNotEquals("types are the same.", dollar1, new Object());
	}
	
	@Test
	public void notEqualsNull() {
		BigDecimal amount1 = new BigDecimal("100.15");
		MoneyAmount dollar1 = MoneyAmount.dollar(amount1);
	
		assertNotEquals("types are the same.", dollar1, null);
	}

	@Test
	public void equals() {
		BigDecimal amount = new BigDecimal("100.15");
		MoneyAmount dollar1 = MoneyAmount.dollar(amount);
		MoneyAmount dollar2 = MoneyAmount.dollar(amount);

		assertEquals("currencies are not the same.", dollar1, dollar2);
	}
	
	@Test
	public void equalsIdentity() {
		BigDecimal amount = new BigDecimal("100.15");
		MoneyAmount dollar = MoneyAmount.dollar(amount);

		assertEquals("currencies are not the same.", dollar, dollar);
	}

	@Test
	public void hashcode() {
		BigDecimal amount = new BigDecimal("100.15");
		MoneyAmount dollar1 = MoneyAmount.dollar(amount);
		MoneyAmount dollar2 = MoneyAmount.dollar(amount);

		assertEquals("currencies are not the same.", Integer.valueOf(dollar1.hashCode()),
				Integer.valueOf(dollar2.hashCode()));
	}

}
