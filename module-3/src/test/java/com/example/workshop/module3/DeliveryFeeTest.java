package com.example.workshop.module3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.testng.annotations.Test;

public class DeliveryFeeTest {

    private final DeliveryFee fee = new DeliveryFee();

    @Test
    public void isFreeAtTheThreshold() {
        assertThat(fee.calculate(100, 1, "EU", false)).isEqualTo(0);
    }

    @Test
    public void chargesJustBelowTheThreshold() {
        assertThat(fee.calculate(99.99, 1, "EU", false)).isEqualTo(8.0);
    }

    @Test
    public void expressIsNeverFree() {
        assertThat(fee.calculate(150, 1, "EU", true)).isEqualTo(16.0);
    }

    @Test
    public void chargesPerZone() {
        assertThat(fee.calculate(10, 1, "LOCAL", false)).isEqualTo(3.0);
        assertThat(fee.calculate(10, 1, "EU", false)).isEqualTo(8.0);
        assertThat(fee.calculate(10, 1, "WORLD", false)).isEqualTo(15.0);
    }

    @Test
    public void addsMediumWeightSurchargeOverFiveKg() {
        assertThat(fee.calculate(10, 5, "LOCAL", false)).isEqualTo(3.0);
        assertThat(fee.calculate(10, 5.1, "LOCAL", false)).isEqualTo(7.0);
    }

    @Test
    public void addsHeavySurchargeOverTwentyKg() {
        assertThat(fee.calculate(10, 20, "LOCAL", false)).isEqualTo(7.0);
        assertThat(fee.calculate(10, 20.1, "LOCAL", false)).isEqualTo(13.0);
    }

    @Test
    public void doublesTheWholeFeeForExpress() {
        assertThat(fee.calculate(10, 6, "EU", true)).isEqualTo(24.0);
    }

    @Test
    public void rejectsNegativeTotal() {
        assertThatThrownBy(() -> fee.calculate(-1, 1, "EU", false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void rejectsNegativeWeight() {
        assertThatThrownBy(() -> fee.calculate(10, -1, "EU", false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void acceptsZeroTotalAndZeroWeight() {
        assertThat(fee.calculate(0, 0, "LOCAL", false)).isEqualTo(3.0);
    }

    @Test
    public void rejectsUnknownZone() {
        assertThatThrownBy(() -> fee.calculate(10, 1, "MARS", false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("MARS");
    }
}
