package com.example.workshop.module3;

import java.util.Locale;

/** Formats an amount for display, for example "EUR 12.50" or "-EUR 3.00". */
public class PriceFormatter {

    public String format(double amount, String currency) {
        if (amount < 0) {
            return "-" + format(-amount, currency);
        }
        return String.format(Locale.ROOT, "%s %.2f", currency, amount);
    }
}
