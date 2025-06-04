package org.rewedigital.models;

import org.rewedigital.Enums;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {
    private String IBAN;
    private BigDecimal Balance;
    private LocalDate CreationDate;
    private Enums.AccountType AccountType;
    private Bank bank;
    private Owner owner;

    public Account(String IBAN, Owner owner, Bank bank, Enums.AccountType accountType, LocalDate creationDate, BigDecimal balance) {
        this.IBAN = IBAN;
        this.owner = owner;
        this.bank = bank;
        AccountType = accountType;
        CreationDate = creationDate;
        Balance = balance;
    }

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }

    public String getIBAN() {
        return IBAN;
    }

    public BigDecimal getBalance() {
        return Balance;
    }

    public LocalDate getCreationDate() {
        return CreationDate;
    }

    public Enums.AccountType getAccountType() {
        return AccountType;
    }

    public Bank getBank() {
        return bank;
    }

    public Owner getOwner() {
        return owner;
    }
}
