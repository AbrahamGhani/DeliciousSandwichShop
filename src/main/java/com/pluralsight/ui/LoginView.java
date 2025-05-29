package com.pluralsight.ui;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class LoginView {

    /*
     * CLI screen for logging in.
     *
     * Fields:
     * - scanner: Scanner
     * - controller: UIControl
     *
     * Methods:
     * - promptCredentials(): Map<String,String>
     */

    private UIControl controller;
    private Scanner scanner;

    public LoginView(UIControl controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public Map<String, String> promptCredentials() {
        Map<String, String> creds = new HashMap<>();

        System.out.println("=== Login Menu ===");
        System.out.println("1. Login as Manager");
        System.out.println("2. Login as User");
        System.out.println("3. Continue as Guest");
        System.out.println("0. Exit");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Manager Username: ");
                creds.put("username", scanner.nextLine());
                System.out.print("Manager Password: ");
                creds.put("password", scanner.nextLine());
                creds.put("role", "manager");
                break;
            case "2":
                System.out.print("User Name: ");
                creds.put("username", scanner.nextLine());
                creds.put("role", "user");
                break;
            case "3":
                creds.put("username", "guest");
                creds.put("role", "guest");
                break;
            case "0":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option.");
                return promptCredentials(); // retry
        }

        return creds;
    }



}
