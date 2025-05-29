package com.pluralsight.order;

import com.pluralsight.core.Priceable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order<T extends Priceable> {

    /*
     * Generic class representing an order of Priceable items.
     *
     * Fields:
     * - id: String
     * - items: List<T>
     * - timestamp: String or LocalDateTime depends on how i want to store and utilize it
     *
     * Methods:
     * - addItem(item: T): void
     * - void removeItem(T item)
     * - getTotalPrice(): double
     * - List<T> getItems()
     *
     * T is typically MenuItem or subclass of it
     */


    private String id;
    private List<T> items;
    private LocalDateTime timestamp;

    public Order(String id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(T item) {
        items.remove(item);
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(Priceable::getPrice)
                .sum();
    }

    public List<T> getItems() {
        return items;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
