package org.rewedigital.models;

import org.rewedigital.exceptions.InsufficientFundsException;
import org.rewedigital.exceptions.InsufficientStockException;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Cashier {
    private final String id;
    private String name;

    public Cashier(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Receipt issueReceipt(Map<Product, Integer> basket, double customerMoney, Shop shop,
                                double foodMarkup, double nonFoodMarkup, double discountPercentage, int discountDaysThreshold)
            throws InsufficientFundsException, InsufficientStockException, IOException {

        Map<String, Double> goodsPrices = new HashMap<>();
        double totalCost = 0.0;

        for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            double markup = product.getCategory().equals("food") ? foodMarkup : nonFoodMarkup;
            double sellingPrice = product.calculateSellingPrice(markup, discountPercentage, discountDaysThreshold);

            if (sellingPrice < 0 || product.isExpired()) {
                throw new InsufficientStockException(product.getName() + " is expired or not sellable");
            }

            product.reduceStock(quantity);
            double cost = sellingPrice * quantity;
            goodsPrices.put(product.getId(), cost);
            totalCost += cost;
        }

        if (customerMoney < totalCost) {
            throw new InsufficientFundsException("Insufficient funds for purchase.");
        }

        Receipt receipt = new Receipt(id, goodsPrices, LocalDate.now(), totalCost);
        shop.addIncome(totalCost);
        saveReceiptToFile(receipt);

        return receipt;
    }

    private void saveReceiptToFile(Receipt receipt) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(receipt.getId() + ".txt"))) {
            pw.println("Receipt ID: " + receipt.getId());
            pw.println("Cashier Name: " + name);
            pw.println("Date of Purchase: " + receipt.getDateOfPurchase());
            pw.println("Products Purchased: ");
            for (Map.Entry<String, Double> entry : receipt.getProductsPrices().entrySet()) {
                pw.println("- Products ID: " + entry.getKey() + ", Total Price: " + entry.getValue());
            }
            pw.println("Total Amount: " + receipt.getTotalAmount());
        }
    }
}
