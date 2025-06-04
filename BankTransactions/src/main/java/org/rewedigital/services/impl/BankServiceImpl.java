package org.rewedigital.services.impl;

import org.rewedigital.Enums;
import org.rewedigital.models.Account;
import org.rewedigital.services.BankService;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {
    private final BigDecimal withdrawNonVipLimit = BigDecimal.valueOf(200);
    private final BigDecimal withdrawVipLimit = BigDecimal.valueOf(400);
    private final BigDecimal withdrawNonVipATMLimit = BigDecimal.valueOf(100);
    private final BigDecimal withdrawVipATMLimit = BigDecimal.valueOf(200);

    public boolean Deposit(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        return true;
    }
    public boolean Withdraw(Enums.WithdrawType withdrawType, Account account, BigDecimal amount) {
        var isVIP = account.getAccountType().equals(Enums.AccountType.VIP);
        if(withdrawType == Enums.WithdrawType.ATM) {
            if (!isVIP && amount.compareTo(withdrawNonVipATMLimit) > 0) {
                return false;
            }

            if (isVIP && amount.compareTo(withdrawVipATMLimit) > 0) {
                return false;
            }
        } else {
            if (!isVIP && amount.compareTo(withdrawNonVipLimit) > 0) {
                return false;
            }

            if (isVIP && amount.compareTo(withdrawVipLimit) > 0) {
                return false;
            }
        }
        if(withdrawType == Enums.WithdrawType.ATM){
            var fee = amount.multiply(account.getBank().getAtmFee().divide(BigDecimal.valueOf(100)));
            account.setBalance(account.getBalance().subtract(amount.add(fee)));
        }
        else {
            account.setBalance(account.getBalance().subtract(amount));
        }

        return true;
    }

    public boolean Transfer(Enums.PaymentType paymentType, Account account, BigDecimal amount) {
        if(paymentType == Enums.PaymentType.ONLINE){
            var fee = amount.multiply(account.getBank().getOnlineFee().divide(BigDecimal.valueOf(100)));
            account.setBalance(account.getBalance().subtract(amount.add(fee)));
        } else if(paymentType == Enums.PaymentType.ONLINE_FAST){
            var fee = amount.multiply(account.getBank().getOnlineFee().divide(BigDecimal.valueOf(100)));
            var feeExtra = amount.multiply(account.getBank().getFastOnlineFee().divide(BigDecimal.valueOf(100)));
            account.setBalance(account.getBalance().subtract(amount.add(fee).add(feeExtra)));
        }
        else {
            account.setBalance(account.getBalance().subtract(amount));
        }

        return true;
    }
}
