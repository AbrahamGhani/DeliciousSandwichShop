package com.pluralsight.menu;

import com.pluralsight.core.MenuItem;

public class Chips extends MenuItem {

    /*
     * Subclass of MenuItem that models a chips item.
     *
     * Fields:
     * - type: String
     * - size: String
     *
     * Inherits:
     * - name, basePrice from MenuItem
     *
     * Implements:
     * - getPrice(): double
     */


    private String type;
    private String size;

    public Chips(String name, double basePrice, String type, String size) {
        super(name, basePrice);
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return getBasePrice();  // simple price logic
    }


}
