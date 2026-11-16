package com.example.workshop.module3;

/** Decides what discount rate (0.0 to 0.5) an order receives. */
public class DiscountPolicy {

    private static final double MAX_DISCOUNT = 0.5;

    public double rateFor(String tier, int ordersThisYear, double orderTotal, String coupon) {
        double rate = 0;

        if ("GOLD".equals(tier)) {
            rate = 0.10;
            if (ordersThisYear >= 20) {
                rate += 0.05;
            }
        } else if ("SILVER".equals(tier)) {
            rate = 0.05;
            if (ordersThisYear >= 10) {
                rate += 0.03;
            }
        } else if (ordersThisYear == 0) {
            rate = 0.02;
        }

        if (orderTotal >= 500) {
            rate += 0.05;
        } else if (orderTotal >= 200) {
            rate += 0.02;
        }

        if (coupon != null && coupon.startsWith("SAVE")) {
            rate += 0.10;
        } else if ("WELCOME".equals(coupon) && ordersThisYear == 0) {
            rate += 0.15;
        }

        return Math.min(rate, MAX_DISCOUNT);
    }
}
