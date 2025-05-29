package com.pluralsight.ui;

import com.pluralsight.menu.*;
import com.pluralsight.toppings.*;
import java.util.Scanner;


public class MenuItemsView {

    /*
     * CLI view for displaying menu items.
     *
     * Fields:
     * - controller: UIControl
     *
     * Methods:
     * - displayItems(items: List<MenuItem>): void
     */

    private UIControl controller;
    private Scanner scanner;

    public MenuItemsView(UIControl controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayItems() {
        while (true) {
            System.out.println("\n=== Menu Items ===");
            System.out.println("1. Sandwiches");
            System.out.println("2. Drinks");
            System.out.println("3. Chips");
            System.out.println("0. Back");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    displaySandwiches();
                    break;
                case "2":
                    displayDrinks();
                    break;
                case "3":
                    displayChips();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void displaySandwiches() {
        while (true) {
            System.out.println("\n=== Sandwich Menu ===");
            System.out.println("1. BLT");
            System.out.println("2. Philly Cheesesteak");
            System.out.println("3. Build Your Own Sandwich");
            System.out.println("0. Back");

            String choice = scanner.nextLine();
            Sandwich sandwich = null;

            switch (choice) {
                case "1":
                    sandwich = new Sandwich("BLT", 7.00, "White", "8\"");
                    sandwich.addTopping(new PremiumTopping("Bacon", 2.00));
                    sandwich.addTopping(new RegularTopping("Lettuce"));
                    sandwich.addTopping(new RegularTopping("Tomato"));
                    sandwich.setToasted(true);
                    break;
                case "2":
                    sandwich = new Sandwich("Philly Cheesesteak", 8.00, "White", "8\"");
                    sandwich.addTopping(new PremiumTopping("Steak", 2.00));
                    sandwich.addTopping(new PremiumTopping("American Cheese", 1.50));
                    sandwich.addTopping(new RegularTopping("Peppers"));
                    sandwich.addTopping(new RegularTopping("Mayo"));
                    sandwich.setToasted(true);
                    break;
                case "3":
                    sandwich = buildCustomSandwich();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

            if (sandwich != null) {
                controller.addItemToCart(sandwich);
                System.out.println(sandwich.getName() + " added to your order.");
            }
        }
    }

    private Sandwich buildCustomSandwich() {
        System.out.println("\n=== Build Your Own Sandwich ===");

        // Bread
        System.out.print("Choose bread (White/Wheat/Rye/Wrap): ");
        String bread = scanner.nextLine();

        // Size
        String size = promptSizeWithValidation("sandwich", new String[]{"4\"", "8\"", "12\""});
        double basePrice = switch (size) {
            case "4\"" -> 5.50;
            case "8\"" -> 7.00;
            case "12\"" -> 8.50;
            default -> 7.00;
        };

        // Create sandwich
        Sandwich custom = new Sandwich("Custom Sandwich", basePrice, bread, size);

        // Toasted
        System.out.print("Toast it? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            custom.setToasted(true);
        }

        // Meats
        String[] meats = {"Bacon", "Ham", "Steak", "Turkey"};
        for (String meat : meats) {
            System.out.printf("Add %s? (y/n): ", meat);
            if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                custom.addTopping(new PremiumTopping(meat, 0));  // base meat

                System.out.printf("Add extra %s? (y/n): ", meat);
                if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                    custom.addTopping(new PremiumTopping(meat + " (extra)", 0));
                }
            }
        }

        // Cheeses
        String[] cheeses = {"Swiss", "Cheddar", "Provolone"};
        for (String cheese : cheeses) {
            System.out.printf("Add %s cheese? (y/n): ", cheese);
            if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                custom.addTopping(new PremiumTopping(cheese, 0));  // base cheese

                System.out.printf("Add extra %s cheese? (y/n): ", cheese);
                if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                    custom.addTopping(new PremiumTopping(cheese + " (extra)", 0));
                }
            }
        }

        // Regular toppings
        String[] regulars = {"Lettuce", "Tomato", "Cucumber", "Mushrooms", "Onions", "Pickles"};
        for (String topping : regulars) {
            System.out.printf("Add %s? (y/n): ", topping);
            if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                custom.addTopping(new RegularTopping(topping));
            }
        }

        // Sauces
        String[] sauces = {"Mayo", "Mustard", "Chipotle", "Ranch"};
        for (String sauce : sauces) {
            System.out.printf("Add %s? (y/n): ", sauce);
            if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                custom.addTopping(new RegularTopping(sauce));
            }
        }

        return custom;
    }



    public void displayDrinks() {
        System.out.println("\n=== Choose Your Drink ===");
        System.out.println("1. Cola");
        System.out.println("2. Sprite");
        System.out.println("3. Tea");
        System.out.println("4. Lemonade");
        System.out.println("0. Back");

        String flavor = null;
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": flavor = "Cola"; break;
            case "2": flavor = "Sprite"; break;
            case "3": flavor = "Tea"; break;
            case "4": flavor = "Lemonade"; break;
            case "0": return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String size = promptSizeWithValidation("drink", new String[]{"small", "medium", "large"});
        double price = switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 2.50;
        };

        Drink drink = new Drink(flavor, price, flavor, capitalize(size));
        controller.addItemToCart(drink);
        System.out.println(capitalize(size) + " " + flavor + " added to your order.");
    }



    public void displayChips() {
        System.out.println("\n=== Chips Menu ===");
        System.out.println("Choose a flavor:");
        System.out.println("1. BBQ");
        System.out.println("2. Sour Cream");
        System.out.println("3. Jalapeno");
        System.out.println("0. Back");

        String flavor = null;
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": flavor = "BBQ"; break;
            case "2": flavor = "Sour Cream"; break;
            case "3": flavor = "Jalapeno"; break;
            case "0": return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String size = null;
        double price = 0.0;
        boolean validSize = false;

        while (!validSize) {
            System.out.println("Choose a size: 2oz / 5oz");
            String sizeInput = scanner.nextLine().trim();

            switch (sizeInput) {
                case "2oz":
                    size = "2oz";
                    price = 1.00;
                    validSize = true;
                    break;
                case "5oz":
                    size = "5oz";
                    price = 1.75;
                    validSize = true;
                    break;
                default:
                    System.out.println("Invalid size. Please enter either 2oz or 5oz.");
            }
        }

        Chips chips = new Chips(flavor + " Chips", price, flavor, size);
        controller.addItemToCart(chips);
        System.out.println(size + " " + flavor + " chips added to your order.");
    }



    private String promptSizeWithValidation(String itemType, String[] validOptions) {
        while (true) {
            System.out.println("Choose a " + itemType + " size (" + String.join(" / ", validOptions) + "):");
            String input = scanner.nextLine().trim().toLowerCase();
            for (String option : validOptions) {
                if (input.equalsIgnoreCase(option)) {
                    return option;
                }
            }
            System.out.println("Invalid " + itemType + " size. Try again.");
        }
    }



    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }



}




