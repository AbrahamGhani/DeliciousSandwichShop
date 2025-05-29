package com.pluralsight.authentication;

public class LoginManager {


    /*
     * Authenticates a Manager based on username and password.
     */
    public static Manager authenticate(String user, String pass) {
        // In a real app, this would check a database or file.
        if (user.equals("admin") && pass.equals("password123")) {
            return new Manager(user, pass);  // assuming Manager has this constructor
        }

        return null; // authentication failed
    }

}
