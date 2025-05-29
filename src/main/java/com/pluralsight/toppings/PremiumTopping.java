package com.pluralsight.toppings;

public class PremiumTopping extends Topping{
    /*
     * A premium topping with additional cost.
     * Subclass of Topping.
     *
     * Fields:
     * - name: String (inherited)
     * - price: double (inherited)
     */

    public PremiumTopping(String name, double price) {
        super(name, price);
    }
}
