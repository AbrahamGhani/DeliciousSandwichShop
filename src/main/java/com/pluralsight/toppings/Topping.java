package com.pluralsight.toppings;

import com.pluralsight.core.Priceable;

public abstract class Topping implements Priceable {

    /*
     * Abstract class representing a topping.
     * Implements Priceable.
     *
     * Fields:
     * - name: String
     * - price: double
     *
     * Methods:
     * - getPrice(): double
     */
    private String name;
    private double price;

    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
