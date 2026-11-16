package com.example.workshop.module3;

/** Delivery fee for an order, based on destination, parcel weight and delivery speed. */
public class DeliveryFee {

    static final double FREE_DELIVERY_FROM = 100.0;
    static final double HEAVY_KG = 20.0;
    static final double MEDIUM_KG = 5.0;

    public double calculate(double orderTotal, double weightKg, String zone, boolean express) {
        if (orderTotal < 0 || weightKg < 0) {
            throw new IllegalArgumentException("Total and weight must not be negative");
        }
        if (orderTotal >= FREE_DELIVERY_FROM && !express) {
            return 0;
        }

        double fee;
        switch (zone) {
            case "LOCAL":
                fee = 3.0;
                break;
            case "EU":
                fee = 8.0;
                break;
            case "WORLD":
                fee = 15.0;
                break;
            default:
                throw new IllegalArgumentException("Unknown zone: " + zone);
        }

        if (weightKg > HEAVY_KG) {
            fee += 10.0;
        } else if (weightKg > MEDIUM_KG) {
            fee += 4.0;
        }

        if (express) {
            fee *= 2;
        }
        return fee;
    }
}
