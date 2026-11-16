package com.example.workshop.module3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.testng.annotations.Test;

public class VatCalculatorTest {

    private final VatCalculator calculator = new VatCalculator();

    @Test
    public void appliesTheRateOfEachCountry() {
        assertThat(calculator.vat(100, "IE")).isEqualTo(23.0);
        assertThat(calculator.vat(100, "DE")).isEqualTo(19.0);
        assertThat(calculator.vat(100, "FR")).isEqualTo(20.0);
    }

    @Test
    public void roundsToCents() {
        assertThat(calculator.vat(10.01, "IE")).isEqualTo(2.30);
        assertThat(calculator.vat(0.02, "IE")).isEqualTo(0.0);
        assertThat(calculator.vat(0.03, "IE")).isEqualTo(0.01);
    }

    @Test
    public void isZeroForAZeroAmount() {
        assertThat(calculator.vat(0, "IE")).isEqualTo(0.0);
    }

    @Test
    public void rejectsUnknownCountry() {
        assertThatThrownBy(() -> calculator.vat(100, "XX"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("XX");
    }
}
