package org.rewedigital.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class Receipt implements Serializable {
    private final String id;
    private final String cashierId;
    private final LocalDate dateOfPurchase;
    private final Map<String, Double> productsPrices; // (Goods ID, Price)
    private final double totalAmount;

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Receipt(String cashierId, Map<String, Double> productsPrices, LocalDate dateOfPurchase, double totalAmount) {
        this.id = UUID.randomUUID().toString();
        this.cashierId = cashierId;
        this.dateOfPurchase = dateOfPurchase;
        this.productsPrices = productsPrices;
        this.totalAmount = totalAmount;
    }

    public Map<String, Double> getProductsPrices() {
        return productsPrices;
    }

    public String getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
