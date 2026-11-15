package com.example.workshop.module1;

import java.util.ArrayList;

/**
 * The books a customer is about to order.
 */
public class Basket extends ArrayList<Book> {

    public double totalPrice() {
        double sum = 0;
        for (Book book : this) {
            sum += book.getPrice();
        }
        return sum;
    }
}
