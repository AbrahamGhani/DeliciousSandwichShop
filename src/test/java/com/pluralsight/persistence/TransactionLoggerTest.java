package com.pluralsight.persistence;

import com.pluralsight.menu.Sandwich;
import com.pluralsight.order.Order;
import com.pluralsight.toppings.PremiumTopping;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
class TransactionLoggerTest {

    @Test
    void testSaveOrderToFileCreatesFile() {
        Order<Sandwich> order = new Order<>("test-001");
        Sandwich s = new Sandwich("Unit Sandwich", 4.00, "White", "4\"");
        s.addTopping(new PremiumTopping("Ham", 0));
        order.addItem(s);

        TransactionLogger.saveOrderToFile(order);

        // Check that an order file was created in "orders/"
        File folder = new File("orders");
        assertTrue(folder.exists() && folder.isDirectory());

        File[] files = folder.listFiles();
        assertTrue(files != null && files.length > 0);
    }



}