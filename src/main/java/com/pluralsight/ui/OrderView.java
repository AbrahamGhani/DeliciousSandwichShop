package com.pluralsight.ui;

import com.pluralsight.core.MenuItem;
import com.pluralsight.core.Priceable;

import java.util.List;

public class OrderView {

    /*
     * CLI view for reviewing and finalizing orders.
     *
     * Fields:
     * - controller: UIControl
     *
     * Methods:
     * - displayOrder(items: List<MenuItem>, total: double): void
     */

    private UIControl controller;

    public OrderView(UIControl controller) {
        this.controller = controller;
    }

    public void displayOrder(List<? extends Priceable> items, double total) {
        System.out.println("\n=== Order Summary ===");

        for (int i = 0; i < items.size(); i++) {
            Priceable item = items.get(i);
            System.out.printf("%d. %s: $%.2f%n",
                    i + 1,
                    item.getClass().getSimpleName(),
                    item.getPrice()
            );
        }

        System.out.printf("Total: $%.2f%n", total);
    }

}
