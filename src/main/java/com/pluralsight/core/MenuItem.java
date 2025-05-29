package com.pluralsight.core;

public abstract class MenuItem implements Priceable{


    /*
     * Abstract class for all menu items sold in the system.
     * Implements Priceable.
     *
     * Fields:
     * - name: String
     * - basePrice: double
     *
     * Methods:
     * - getName(): String
     * - setName(name: String): void
     * - getBasePrice(): double
     * - setBasePrice(p: double): void
     * - getPrice(): double

*/

    private String name;
    private double basePrice;

    public MenuItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }



    @Override
    public abstract double getPrice(); // subclasses must define this

}
