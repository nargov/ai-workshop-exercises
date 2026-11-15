package com.example.workshop.module1;

import java.util.List;

/**
 * Places orders for books and e-books.
 */
public class OrderService {

    private final ShippingCalculator shipping;
    private final Mailer mailer;
    private final OrderEventBus events;
    private final RoundingStrategyFactory rounding;

    public OrderService(ShippingCalculator shipping, Mailer mailer, OrderEventBus events,
                        RoundingStrategyFactory rounding) {
        this.shipping = shipping;
        this.mailer = mailer;
        this.events = events;
        this.rounding = rounding;
    }

    public double placeOrder(String customer, String country, List<Book> books, boolean express) {
        double subtotal = 0;
        double weight = 0;
        for (Book book : books) {
            subtotal += book.getPrice();
            weight += book.getWeightKg();
        }
        double total = rounding.get().round(subtotal + shipping.cost(country, weight, express));

        events.publish(new OrderEvent("PLACED", customer, total));
        mailer.send(customer + "@bookstore.example", invoice(books) + "TOTAL: " + total);
        return total;
    }

    private String invoice(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.getIsbn()).append(" ").append(book.getTitle()).append(" ")
                    .append(book.getPrice()).append("\n");
        }
        return sb.toString();
    }
}
