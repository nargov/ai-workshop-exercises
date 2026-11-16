package com.example.workshop.module3;

/** Loyalty points earned for an order: one per 10 spent, doubled for members, capped at 1000. */
public class LoyaltyPoints {

    private static final int CAP = 1000;

    public int pointsFor(double orderTotal, boolean member) {
        int points = (int) (orderTotal / 10);
        if (member) {
            points *= 2;
        }
        return Math.min(points, CAP);
    }
}
