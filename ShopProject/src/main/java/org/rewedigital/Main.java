package org.rewedigital;

import org.rewedigital.exceptions.InsufficientFundsException;
import org.rewedigital.exceptions.InsufficientStockException;
import org.rewedigital.models.Cashier;
import org.rewedigital.models.Product;
import org.rewedigital.models.Receipt;
import org.rewedigital.models.Shop;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product apple = new Product("001", "Apple", "food", LocalDate.now().plusDays(5), 0.5, 100);
        Product shampoo = new Product("002", "Shampoo", "non-food", LocalDate.now().plusDays(15), 3.0, 50);

        // Create a shop and add goods
        Shop shop = new Shop();
        shop.addGoods(apple);
        shop.addGoods(shampoo);

        // Initialize cashier
        Cashier cashier = new Cashier("c001", "John Doe");

        // Simulation of customer basket
        Map<Product, Integer> customerBasket = new HashMap<>();
        customerBasket.put(apple, 10);
        customerBasket.put(shampoo, 2);

        double customerMoney = 50.0;

        try {
            // Cashier issues receipt
            double foodMarkup = 10.0; // 10% markup for food
            double nonFoodMarkup = 20.0; // 20% markup for non-food
            double discountPercentage = 5.0; // Discount if near expiry
            int discountDaysThreshold = 7;

            Receipt receipt = cashier.issueReceipt(customerBasket, customerMoney, shop,
                    foodMarkup, nonFoodMarkup, discountPercentage, discountDaysThreshold);

            if (receipt != null) {
                System.out.println("Receipt issued successfully!");
                System.out.println("Receipt ID: " + receipt.getId());
                System.out.println("Total Amount: " + receipt.getTotalAmount());
            } else {
                System.out.println("Transaction Failed: Insufficient Funds or Goods Stock");
            }
        } catch (InsufficientFundsException | InsufficientStockException | IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Shop's Total Costs: " + shop.calculateTotalCosts());
        System.out.println("Shop's Total Income: " + shop.calculateTotalIncome());
        System.out.println("Shop's Profit: " + shop.calculateProfit());
    }
}