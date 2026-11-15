package com.example.workshop.module1;

public class Book {

    private final String isbn;
    private final String title;
    protected double price;
    private final double weightKg;

    public Book(String isbn, String title, double price, double weightKg) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.weightKg = weightKg;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getWeightKg() {
        return weightKg;
    }
}
