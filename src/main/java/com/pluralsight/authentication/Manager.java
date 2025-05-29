package com.pluralsight.authentication;

public class Manager {

    /*
     * Represents an authenticated manager account.
     * Used to validate login credentials.
     *
     * Fields:
     * - username: String
     * - password: String
     *
     * Methods:
     * - getUsername(): String
     * - getPassword(): String
     */


    private String username;
    private String password;

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
