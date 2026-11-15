package com.example.workshop.module1;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        OrderManager om = new OrderManager();

        String order = om.proc(
                "alice",
                new String[] {"Clean Code", "Refactoring"},
                new int[] {1, 2},
                new double[] {39.9, 47.5},
                "V",
                "SAVE10",
                false);
        System.out.println(order);

        System.out.println("Refund: " + om.procReturn("alice", 47.5, "V", null));

        OrderEventBus bus = new OrderEventBus();
        bus.subscribe(new AuditListener());
        OrderService service = new OrderService(
                new EuShippingCalculator(), new Mailer(), bus, RoundingStrategyFactory.getInstance());
        Basket basket = new Basket();
        basket.add(new Book("9780132350884", "Clean Code", 39.9, 0.6));
        basket.add(new EBook("9780134757599", "Refactoring", 47.5));
        double total = service.placeOrder("bob", "IE", basket, false);
        System.out.println("Service total: " + total);
    }
}
