package com.example.workshop.module3;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class LoyaltyPointsTest {

    private final LoyaltyPoints loyalty = new LoyaltyPoints();

    @Test
    public void earnsPointsForARegularOrder() {
        assertThat(loyalty.pointsFor(100, false)).isGreaterThan(0);
    }

    @Test
    public void membersEarnPointsToo() {
        assertThat(loyalty.pointsFor(100, true)).isGreaterThan(0);
    }

    @Test
    public void largeOrdersAreCapped() {
        assertThat(loyalty.pointsFor(1_000_000, true)).isLessThanOrEqualTo(1000);
    }
}
