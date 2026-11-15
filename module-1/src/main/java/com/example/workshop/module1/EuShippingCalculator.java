package com.example.workshop.module1;

import java.util.Set;

/**
 * Shipping for the European warehouse.
 */
public class EuShippingCalculator extends ShippingCalculator {

    private static final Set<String> EU = Set.of("IE", "DE", "FR", "NL", "ES", "IT");

    @Override
    public double cost(String country, double weightKg, boolean express) {
        if (!EU.contains(country)) {
            throw new IllegalArgumentException("not an EU country: " + country);
        }
        return super.cost(country, weightKg, express);
    }
}
