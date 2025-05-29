package com.pluralsight.ui;

import com.pluralsight.order.Order;
import java.util.List;

public class TransactionView {


    // should only be accessible to the manager
    /*
     * CLI view for listing past transactions.
     *
     * Fields:
     * - controller: UIControl
     *
     * Methods:
     * - displayTransactions(trans: List<Order<?>>): void
     */

    private UIControl controller;

    public TransactionView(UIControl controller) {
        this.controller = controller;
    }
    public void displayRawLogs(List<String> lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public void displayTransactions(List<Order<?>> transactions) {
        if (!controller.isManagerLoggedIn()) {
            System.out.println("Access Denied. Only managers can view transactions.");
            return;
        }

        System.out.println("\n=== Transaction History ===");

        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (int i = 0; i < transactions.size(); i++) {
            Order<?> order = transactions.get(i);
            System.out.printf("Order ID: %s | Time: %s | Total: $%.2f%n",
                    order.getId(),
                    order.getTimestamp(),
                    order.getTotalPrice()
            );
        }
    }

}
