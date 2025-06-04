package org.rewedigital.models;

import org.rewedigital.exceptions.InsufficientStockException;

import java.io.Serializable;
import java.time.LocalDate;

public class Product {
    private final String id;
    private String name;
    private String category; // "food" or "non-food"
    private LocalDate expiryDate;
    private double basePrice;
    private int stock;

    public Product(String id, String name, String category, LocalDate expiryDate, double basePrice, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.expiryDate = expiryDate;
        this.basePrice = basePrice;
        this.stock = stock;
    }

    public double calculateSellingPrice(double markupPercentage, double discountPercentage, int daysToDiscountThreshold) {
        double sellingPrice = basePrice + (basePrice * markupPercentage / 100);
        long daysUntilExpiry = LocalDate.now().until(expiryDate).getDays();
        if (daysUntilExpiry < daysToDiscountThreshold) {
            sellingPrice = sellingPrice - sellingPrice * discountPercentage / 100;
        }
        return daysUntilExpiry <= 0 ? -1 : sellingPrice; // -1 indicates expired goods
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public void reduceStock(int amount) throws InsufficientStockException {
        if (stock < amount) {
            throw new InsufficientStockException("Not enough stock for " + name);
        }
        stock -= amount;
    }

    public int getStock() {
        return stock;
    }
}
