package com.pluralsight.menu;

import com.pluralsight.core.MenuItem;

public class Drink extends MenuItem {
    /*
     * Subclass of MenuItem representing a drink.
     *
     * Fields:
     * - flavor: String
     * - size: String
     *
     * Inherits:
     * - name, basePrice from MenuItem
     *
     * Implements:
     * - getPrice(): double
     */

    private String flavor;
    private String size;

    public Drink(String name, double basePrice, String flavor, String size) {
        super(name, basePrice);
        this.flavor = flavor;
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return getBasePrice(); // could vary by size in the future
    }

}
