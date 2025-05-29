package com.pluralsight.persistence;

import com.pluralsight.core.MenuItem;
import com.pluralsight.core.Priceable;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.order.Order;
import com.pluralsight.toppings.PremiumTopping;
import com.pluralsight.toppings.RegularTopping;
import com.pluralsight.toppings.Topping;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;


public class TransactionLogger {

    /*
     * Logs completed transactions to a file.
     *
     * Fields:
     * - logFilename: String
     *
     * Methods:
     * - logTransaction(order: Order<? extends Priceable>): void
     * - readRawLogs(): List<String>
     */

    private String logFilename;

    public TransactionLogger(String logFilename) {
        this.logFilename = logFilename;
    }

    public void logTransaction(Order<? extends Priceable> order) {
        StringBuilder sb = new StringBuilder();

        sb.append("Order ").append(order.getId())
                .append(" @ ").append(order.getTimestamp())
                .append("\n");

        List<? extends Priceable> items = order.getItems();

        for (int i = 0; i < items.size(); i++) {
            Priceable item = items.get(i);
            sb.append("- ").append(item.getClass().getSimpleName())
                    .append(" - $").append(item.getPrice())
                    .append("\n");
        }

        sb.append("Total: $").append(order.getTotalPrice()).append("\n\n");

        try {
            Path path = Paths.get(logFilename);
            Files.createDirectories(path.getParent());  // ✅ create 'receipts/' if missing
            Files.writeString(path, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Failed to log transaction: " + e.getMessage());
        }
    }

    public List<String> readRawLogs() {
        try {
            return Files.readAllLines(Paths.get(logFilename));
        } catch (IOException e) {
            System.err.println("Failed to read transaction log: " + e.getMessage());
            return Collections.emptyList();
        }
    }


    public static void saveOrderToFile(Order<? extends Priceable> order) {
        try {
            // Ensure the directory exists
            File folder = new File("orders");
            if (!folder.exists()) folder.mkdir();

            // Unique filename
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = "orders/order_" + timestamp + ".txt";

            PrintWriter writer = new PrintWriter(fileName);

            // Header
            writer.println("=== ORDER RECEIPT ===");
            writer.println("Timestamp: " + LocalDateTime.now());
            writer.println("Order ID: " + order.getId());

// Details
            for (Priceable item : order.getItems()) {
                if (item instanceof Sandwich s) {
                    writer.println("\n--- Sandwich ---");
                    writer.println("Bread: " + s.getBreadType());
                    writer.println("Size: " + s.getSize());
                    writer.println("Toasted: " + (s.isToasted() ? "Yes" : "No"));

                    writer.println("Premium Toppings:");
                    for (Topping t : s.getToppings()) {
                        if (t instanceof PremiumTopping) {
                            String name = t.getName();
                            boolean isExtra = name.toLowerCase().contains("extra");
                            double toppingPrice = 0;

                            if (isCheese(name)) {
                                toppingPrice = getCheesePrice(s.getSize(), isExtra);
                            } else if (isMeat(name)) {
                                toppingPrice = getMeatPrice(s.getSize(), isExtra);
                            }

                            writer.printf("  - %s: $%.2f%n", name, toppingPrice);
                        }
                    }

                    writer.println("Regular Toppings:");
                    for (Topping t : s.getToppings()) {
                        if (t instanceof RegularTopping) {
                            writer.println("  - " + t.getName());
                        }
                    }

                    writer.printf("Sandwich Total: $%.2f%n", s.getPrice());

                } else {
                    if (item instanceof MenuItem mi) {
                        writer.printf("\n%s: $%.2f%n", mi.getName(), mi.getPrice());
                    } else {
                        writer.printf("\nUnknown Item: $%.2f%n", item.getPrice());
                    }
                }
            }

            writer.printf("\n=== TOTAL: $%.2f ===%n", order.getTotalPrice());

            writer.close();
            System.out.println("🧾 Saved order: " + fileName);
        } catch (IOException e) {
            System.err.println("❌ Failed to save order: " + e.getMessage());
        }
    }






    private static boolean isMeat(String name) {
        return name.toLowerCase().matches(".*(bacon|ham|steak|turkey).*");
    }

    private static boolean isCheese(String name) {
        return name.toLowerCase().matches(".*(swiss|cheddar|provolone).*");
    }

    private static double getMeatPrice(String size, boolean extra) {
        double base = switch (size) {
            case "4\"" -> 1.00;
            case "8\"" -> 2.00;
            case "12\"" -> 3.00;
            default -> 0;
        };
        return extra ? base * 1.5 : base;
    }

    private static double getCheesePrice(String size, boolean extra) {
        double base = switch (size) {
            case "4\"" -> 0.75;
            case "8\"" -> 1.50;
            case "12\"" -> 2.25;
            default -> 0;
        };
        return extra ? base * 1.5 : base;
    }


}
