package com.daybelge.billing.payment;

import com.daybelge.billing.currency.MoneyAmount;

public interface Payable {
	MoneyAmount calculatePayableAmount();
}
