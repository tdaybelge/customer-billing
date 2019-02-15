package com.daybelge.billing.payment;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.Currency;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.daybelge.billing.currency.CurrencyConverter;
import com.daybelge.billing.currency.MoneyAmount;
import com.daybelge.billing.user.Affiliate;
import com.daybelge.billing.user.Customer;
import com.daybelge.billing.user.Employee;
import com.daybelge.billing.user.User;

public class PaymentTestBase {

	protected User michael = new Affiliate("Michael", "Jackson", "Address 4");
	protected User bob = new Employee("Bob", "Ross", "Address 3", "br97123");
	protected User alice = new Customer("Alice", "Cooper", "Address 2", ZonedDateTime.now().minusYears(2));
	protected User justin = new Customer("Justin", "Bieber", "Address 1", ZonedDateTime.now().minusDays(10));
	
	protected CurrencyConverter converter;

	public void init() {
		converter = mock(CurrencyConverter.class);
		when(converter.convert(any(), any())).then(new Answer<MoneyAmount>() {

			@Override
			public MoneyAmount answer(InvocationOnMock invocation) throws Throwable {
				MoneyAmount amount = (MoneyAmount) invocation.getArguments()[0];
				Currency currency = (Currency) invocation.getArguments()[1];
				if (currency.equals(amount.getCurrency())) {
					return amount;
				} else {
					throw new IllegalArgumentException("Cannot convert to " + currency);
				}
			}

		});
	}
}
