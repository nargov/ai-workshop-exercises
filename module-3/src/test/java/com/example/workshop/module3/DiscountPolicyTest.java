package com.example.workshop.module3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.testng.annotations.Test;

public class DiscountPolicyTest {

    private final DiscountPolicy policy = new DiscountPolicy();

    @Test
    public void goldCustomerWithManyOrdersAndALargeOrderGetsTheBestRate() {
        assertThat(policy.rateFor("GOLD", 25, 600, null)).isCloseTo(0.20, within(1e-9));
    }
}
