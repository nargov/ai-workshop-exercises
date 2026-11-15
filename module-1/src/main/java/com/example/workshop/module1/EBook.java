package com.example.workshop.module1;

/**
 * A digital edition: same data as a book, cheaper, nothing to ship.
 */
public class EBook extends Book {

    public EBook(String isbn, String title, double price) {
        super(isbn, title, price, 0);
        this.price = price * 0.8;
    }
}
