package com.pluralsight.menu;

import com.pluralsight.core.MenuItem;
import com.pluralsight.toppings.PremiumTopping;
import com.pluralsight.toppings.Topping;
import java.util.ArrayList;
import java.util.List;


public class Sandwich extends MenuItem {

    /*
     * Subclass of MenuItem representing a customizable sandwich.
     *
     * Fields:
     * - breadType: String
     * - size: String
     * - toppings: List<Topping>
     * - toasted: boolean
     *
     * Methods:
     * - addTopping(t: Topping): void
     * - removeTopping(t: Topping): void
     * - isToasted(): boolean
     * - setToasted(t: boolean): void
     */

    private String breadType;
    private String size;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(String name, double basePrice, String breadType, String size) {
        super(name, basePrice);
        this.breadType = breadType;
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toasted = false;
    }

    public void addTopping(Topping t) {
        toppings.add(t);
    }

    public void removeTopping(Topping t) {
        toppings.remove(t);
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getSize() {
        return size;
    }




    private boolean isCheese(String name) {
        return name.toLowerCase().contains("swiss") ||
                name.toLowerCase().contains("cheddar") ||
                name.toLowerCase().contains("provolone");
    }

    private boolean isMeat(String name) {
        return name.toLowerCase().contains("bacon") ||
                name.toLowerCase().contains("ham") ||
                name.toLowerCase().contains("steak") ||
                name.toLowerCase().contains("turkey");
    }

    @Override
    public double getPrice() {
        double total = getBasePrice();

        for (Topping topping : toppings) {
            if (topping instanceof PremiumTopping) {
                String name = topping.getName();
                boolean isExtra = name.toLowerCase().contains("extra");

                if (isCheese(name)) {
                    total += getCheesePrice(size, isExtra);
                } else if (isMeat(name)) {
                    total += getMeatPrice(size, isExtra);
                }
            }
        }

        return total;
    }


    private double getMeatPrice(String size, boolean extra) {
        double base = switch (size) {
            case "4\"" -> 1.00;
            case "8\"" -> 2.00;
            case "12\"" -> 3.00;
            default -> 0;
        };
        return extra ? base * 1.5 : base;
    }

    private double getCheesePrice(String size, boolean extra) {
        double base = switch (size) {
            case "4\"" -> 0.75;
            case "8\"" -> 1.50;
            case "12\"" -> 2.25;
            default -> 0;
        };
        return extra ? base * 1.5 : base;
    }

}
