package com.daybelge.billing.currency;

import java.util.Currency;

public interface CurrencyConverter {
	MoneyAmount convert(MoneyAmount moneyAmount, Currency currency);
}
