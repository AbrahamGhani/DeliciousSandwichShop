package com.pluralsight.menu;

import com.pluralsight.toppings.PremiumTopping;
import com.pluralsight.toppings.RegularTopping;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SandwichTest {

    @Test
    void testSandwichTotalWithPremiumToppings() {
        Sandwich sandwich = new Sandwich("Test Sandwich", 5.00, "Wheat", "8\"");
        sandwich.addTopping(new PremiumTopping("Ham", 0));                  // 2.00
        sandwich.addTopping(new PremiumTopping("Cheddar (extra)", 0));      // 2.25
        sandwich.addTopping(new RegularTopping("Lettuce"));                 // 0.00

        double expectedTotal = 5.00 + 2.00 + 2.25; // 9.25
        assertEquals(expectedTotal, sandwich.getPrice(), 0.001);
    }
}