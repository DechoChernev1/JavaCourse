package org.rewedigital.services;

import org.rewedigital.Enums;
import org.rewedigital.models.Account;

import java.math.BigDecimal;

public interface BankService {
    public boolean Deposit(Account account, BigDecimal amount);
    public boolean Withdraw(Enums.WithdrawType withdrawType, Account account, BigDecimal amount);
    public boolean Transfer(Enums.PaymentType paymentType, Account account, BigDecimal amount);
}
