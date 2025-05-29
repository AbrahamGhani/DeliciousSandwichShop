package com.pluralsight;
import com.pluralsight.ui.UIControl;
public class Main {
    public static void main(String[] args) {

        /*
         * Entry point of the CLI application.
         * - Instantiates UIControl
         * - Starts the CLI loop
         */
        UIControl ui = new UIControl();
        ui.startApp();



    }
}