package org.rewedigital;

import org.rewedigital.exceptions.DepositException;
import org.rewedigital.models.Account;
import org.rewedigital.models.Bank;
import org.rewedigital.models.Person;
import org.rewedigital.services.BankService;
import org.rewedigital.services.impl.BankServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws DepositException {
        System.out.println("Hello and welcome!");
        var person = new Person("ignId1", "Decho");
        var bank = new Bank("DSK", BigDecimal.valueOf(2.05), BigDecimal.valueOf(4.05), BigDecimal.valueOf(20.05));
        var account = new Account("IBAN1", person, bank, Enums.AccountType.REGULAR, LocalDate.now(), BigDecimal.valueOf(120.05));
        var accountVIP = new Account("IBAN2", person, bank, Enums.AccountType.VIP, LocalDate.now(), BigDecimal.valueOf(120.05));
        var bankService = new BankServiceImpl();

        var result = bankService.Deposit(account, BigDecimal.valueOf(120.05));

        if(!result){
            throw new DepositException();
        }

        System.out.println("Acc: " + account.getBalance());

        var result1 = bankService.Withdraw(Enums.WithdrawType.ATM, account, BigDecimal.valueOf(20.05));
        System.out.println("After Withdraw ATM");
        System.out.println("Acc: " + account.getBalance());

        var result2 = bankService.Withdraw(Enums.WithdrawType.BANK_DESK, account, BigDecimal.valueOf(30.05));
        System.out.println("After Withdraw");
        System.out.println("Acc: " + account.getBalance());

        var result3 = bankService.Withdraw(Enums.WithdrawType.ATM, account, BigDecimal.valueOf(120.05));
        System.out.println("After Withdraw");
        System.out.println("Acc: " + account.getBalance());

        if(!result3){
            System.out.println("Withdraw exception over the limit for Non VIP");//Throw exception
        }

        var result4 = bankService.Withdraw(Enums.WithdrawType.BANK_DESK, account, BigDecimal.valueOf(220.05));
        System.out.println("After Withdraw");
        System.out.println("Acc: " + account.getBalance());

        if(!result4){
            System.out.println("Withdraw exception over the limit for Non VIP");//Throw exception
        }

        var result5 = bankService.Withdraw(Enums.WithdrawType.ATM, accountVIP, BigDecimal.valueOf(220.05));
        System.out.println("After Withdraw");
        System.out.println("Acc: " + accountVIP.getBalance());

        if(!result5){
            System.out.println("Withdraw exception over the limit for VIP");//Throw exception
        }

        var result6 = bankService.Withdraw(Enums.WithdrawType.BANK_DESK, accountVIP, BigDecimal.valueOf(420.05));
        System.out.println("After Withdraw");
        System.out.println("Acc: " + accountVIP.getBalance());

        if(!result6){
            System.out.println("Withdraw exception over the limit for VIP");//Throw exception
        }

        var result7 = bankService.Transfer(Enums.PaymentType.ONLINE, accountVIP, BigDecimal.valueOf(50.05));
        System.out.println("After Transfer");
        System.out.println("Acc: " + accountVIP.getBalance());

        var result8 = bankService.Transfer(Enums.PaymentType.ONLINE_FAST, accountVIP, BigDecimal.valueOf(50.05));
        System.out.println("After Transfer");
        System.out.println("Acc: " + accountVIP.getBalance());

        var result9 = bankService.Transfer(Enums.PaymentType.POS, accountVIP, BigDecimal.valueOf(2.05));
        System.out.println("After Transfer");
        System.out.println("Acc: " + accountVIP.getBalance());
    }
}
