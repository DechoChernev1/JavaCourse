package org.rewedigital.models;

import java.math.BigDecimal;

public class Bank {
    private String name;
    private BigDecimal atmFee;
    private BigDecimal onlineFee;
    private BigDecimal fastOnlineFee;

    public Bank(String name, BigDecimal atmFee, BigDecimal onlineFee, BigDecimal fastOnlineFee) {
        this.name = name;
        this.atmFee = atmFee;
        this.onlineFee = onlineFee;
        this.fastOnlineFee = fastOnlineFee;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAtmFee() {
        return atmFee;
    }

    public BigDecimal getFastOnlineFee() {
        return fastOnlineFee;
    }

    public BigDecimal getOnlineFee() {
        return onlineFee;
    }
}
