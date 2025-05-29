package com.pluralsight.ui;

import com.pluralsight.authentication.*;
import com.pluralsight.core.*;
import com.pluralsight.order.*;
import com.pluralsight.persistence.TransactionLogger;

import java.util.*;

public class UIControl {



    /*
 * Controller class for coordinating UI events and logic.

 * Fields:
 * - manager: Manager
 * - currentOrder: Order<Priceable>
 * - loginView: LoginView
 * - mainMenu: MainMenu
 * - menuItemsView: MenuItemsView
 * - orderView: OrderView
 * - transactionView: TransactionView

*
 * Methods:
 * - startApp(): void
 * - onLogin(user: String, pass: String): void
 * - showMenu(): void
 * - showItems(): void
 * - showOrder(): void
 * - showTransactions(): void
 */

    private Manager manager;
    private User user;
    private Order<Priceable> currentOrder;
    private LoginView loginView;
    private MainMenu mainMenu;
    private MenuItemsView menuItemsView;
    private OrderView orderView;
    private TransactionView transactionView;

    public UIControl() {
        this.loginView = new LoginView(this);
        this.mainMenu = new MainMenu(this);
        this.menuItemsView = new MenuItemsView(this);
        this.orderView = new OrderView(this);
        this.transactionView = new TransactionView(this);
    }

    public void startApp() {
        Map<String, String> creds = loginView.promptCredentials();
        String role = creds.get("role");

        if ("manager".equals(role)) {
            onManagerLogin(creds.get("username"), creds.get("password"));
        } else {
            onUserLogin(creds.get("username"), role);
        }
    }

    public void onManagerLogin(String username, String password) {
        Manager m = LoginManager.authenticate(username, password);
        if (m != null) {
            this.manager = m;
            System.out.println("Manager login successful. Welcome, " + m.getUsername());
            showMenu();
        } else {
            System.out.println("Login failed. Try again.");
            startApp();
        }
    }

    public boolean isManagerLoggedIn() {
        return manager != null;
    }



    public void onUserLogin(String username, String role) {
        this.user = new User(username);
        System.out.println("Welcome, " + username + " (" + role + ")");
        showMenu();
    }

    public void showMenu() {
        mainMenu.displayOptions();
    }

    public void showMenuItemsView() {
        menuItemsView.displayItems();
    }

    public void showOrderView() {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double total = 0;
        List<Priceable> items = currentOrder.getItems();
        for (int i = 0; i < items.size(); i++) {
            Priceable item = items.get(i);
            System.out.printf("%d. %s: $%.2f%n",
                    i + 1,
                    item.getClass().getSimpleName(),
                    item.getPrice()
            );
            total += item.getPrice();
        }

        System.out.printf("Total: $%.2f%n", total);

        System.out.print("Proceed to checkout? (y/n): ");
        String confirm = new Scanner(System.in).nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            // log transaction
            TransactionLogger logger = new TransactionLogger("receipts/transactions.txt");
            logger.logTransaction(currentOrder);
            TransactionLogger.saveOrderToFile(currentOrder);
            System.out.println("✅ Order has been saved and checked out.");

            // clear cart
            currentOrder = null;
        } else {
            System.out.println("Returning to main menu...");
        }
    }

    public void showTransactionView() {
        if (manager == null) {
            System.out.println("Access Denied. Only managers can view transactions.");
            return;
        }

        TransactionLogger logger = new TransactionLogger("receipts/transactions.txt");
        List<String> rawLogs = logger.readRawLogs();
        transactionView.displayRawLogs(rawLogs);
    }

    public void addItemToCart(Priceable item) {
        if (currentOrder == null) {
            currentOrder = new Order<>("ORDER-" + System.currentTimeMillis());
        }
        currentOrder.addItem(item);
    }



}
