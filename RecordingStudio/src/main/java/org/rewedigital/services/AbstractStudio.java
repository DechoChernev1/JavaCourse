package org.rewedigital.services;

public abstract class AbstractStudio {
    private final String id;
    protected double priceInBGN;
    protected int maxHoursPerDay;
    protected int rentedHours;
    protected static final double minPriceInBGN = 30.0; // Minimum rental price
    protected static double exchangeRateEURtoBGN = 1.95583;

    public AbstractStudio(String id, double priceInBGN, int maxHoursPerDay) {
        this.id = id;
        setPrice(priceInBGN);
        setMaxHoursPerDay(maxHoursPerDay);
    }

    public String getId() {
        return id;
    }

    public void setPrice(double priceInBGN) {
        if (priceInBGN < minPriceInBGN) {
            this.priceInBGN = minPriceInBGN;
        } else {
            this.priceInBGN = priceInBGN;
        }
    }

    public void setMaxHoursPerDay(int maxHoursPerDay) {
        if (maxHoursPerDay < 0 || maxHoursPerDay > 24) {
            throw new IllegalArgumentException("Max hours per day must be between 0 and 24.");
        }
        this.maxHoursPerDay = maxHoursPerDay;
    }

    public void setRentedHours(int rentedHours) {
        if (rentedHours < 0 || rentedHours > maxHoursPerDay) {
            throw new IllegalArgumentException("Rented hours cannot exceed maximum allowed hours per day.");
        }
        this.rentedHours = rentedHours;
    }
}
