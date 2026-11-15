package com.example.workshop.module1;

public class ShippingCalculator {

    public double cost(String country, double weightKg, boolean express) {
        double base;
        switch (country) {
            case "IL":
                base = 5;
                break;
            case "US":
                base = 12;
                break;
            case "CA":
                base = 12;
                break;
            case "GB":
                base = 9;
                break;
            case "IE":
                base = 9;
                break;
            case "DE":
                base = 8;
                break;
            case "FR":
                base = 8;
                break;
            case "NL":
                base = 8;
                break;
            case "ES":
                base = 8.5;
                break;
            case "IT":
                base = 8.5;
                break;
            case "AU":
                base = 18;
                break;
            case "NZ":
                base = 18;
                break;
            case "JP":
                base = 15;
                break;
            case "CN":
                base = 15;
                break;
            default:
                base = 20;
        }

        double perKg;
        switch (country) {
            case "IL":
                perKg = 1.0;
                break;
            case "US":
            case "CA":
                perKg = 2.0;
                break;
            case "GB":
            case "IE":
                perKg = 1.5;
                break;
            case "DE":
            case "FR":
            case "NL":
            case "ES":
            case "IT":
                perKg = 1.2;
                break;
            case "AU":
                perKg = 3.0;
                break;
            case "NZ":
                perKg = 3.0;
            case "JP":
                perKg = 2.5;
                break;
            case "CN":
                perKg = 2.5;
                break;
            default:
                perKg = 4.0;
        }

        double surcharge;
        switch (country) {
            case "IL":
                surcharge = express ? 10 : 0;
                break;
            case "US":
            case "CA":
                surcharge = express ? 25 : 0;
                break;
            case "GB":
            case "IE":
            case "DE":
            case "FR":
            case "NL":
            case "ES":
            case "IT":
                surcharge = express ? 15 : 0;
                break;
            default:
                surcharge = express ? 40 : 0;
        }

        return base + perKg * weightKg + surcharge;
    }
}
