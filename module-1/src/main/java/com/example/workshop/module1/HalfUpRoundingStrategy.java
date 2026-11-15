package com.example.workshop.module1;

public class HalfUpRoundingStrategy implements RoundingStrategy {

    @Override
    public double round(double value) {
        return Math.round(value * 100) / 100.0;
    }
}
