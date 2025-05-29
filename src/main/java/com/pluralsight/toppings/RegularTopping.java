package com.pluralsight.toppings;

public class RegularTopping extends Topping{

    /*
     * A premium topping with additional cost.
     * Subclass of Topping.
     *
     * Fields:
     * - name: String (inherited)
     * - price: double (inherited)
     */

    public RegularTopping(String name) {
        super(name, 0.25); // small fixed price for regular toppings
    }

}
