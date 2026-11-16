package com.example.workshop.module3;

import java.util.Map;

/** VAT on a net amount, rounded to cents. */
public class VatCalculator {

    private static final Map<String, Double> RATES = Map.of("IE", 0.23, "DE", 0.19, "FR", 0.20);

    public double vat(double net, String country) {
        Double rate = RATES.get(country);
        if (rate == null) {
            throw new IllegalArgumentException("No VAT rate for " + country);
        }
        return Math.round(net * rate * 100) / 100.0;
    }
}
