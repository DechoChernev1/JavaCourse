package org.rewedigital.models;

import java.util.HashMap;
import java.util.Map;

public class Shop {
    private Map<String, Product> stock = new HashMap<>();
    private double totalCost = 0.0;
    private double totalIncome = 0.0;

    public void addGoods(Product product) {
        stock.put(product.getId(), product);
    }

    public void addIncome(double income) {
        totalIncome += income;
    }

    public double calculateTotalCosts() {
        return totalCost;
    }

    public double calculateTotalIncome() {
        return totalIncome;
    }

    public double calculateProfit() {
        return totalIncome - totalCost;
    }
}
