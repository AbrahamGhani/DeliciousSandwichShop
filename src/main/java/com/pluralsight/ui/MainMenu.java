package com.pluralsight.ui;

import java.util.Scanner;

public class MainMenu {

    /*
     * CLI screen for showing top-level options.
     *
     * Fields:
     * - scanner: Scanner
     * - controller: UIControl
     *
     * Methods:
     * - displayOptions(): void
     */
    private Scanner scanner;
    private UIControl controller;

    public MainMenu(UIControl controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions() {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Menu Items");
            System.out.println("2. Checkout");
            if (controller.isManagerLoggedIn()) {
                System.out.println("3. View Transactions");
            }
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    controller.showMenuItemsView();
                    break;
                case "2":
                    controller.showOrderView();
                    break;
                case "3":
                    if (controller.isManagerLoggedIn()) {
                        controller.showTransactionView();
                    } else {
                        System.out.println("Unauthorized.");
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
