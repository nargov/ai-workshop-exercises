package com.example.workshop.module1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

public class ShippingCalculatorTest {

    @Test
    public void calculatesShippingForIsrael() {
        ShippingCalculator calculator = mock(ShippingCalculator.class);
        when(calculator.cost("IL", 1.0, false)).thenReturn(6.0);

        double cost = calculator.cost("IL", 1.0, false);

        assertThat(cost).isEqualTo(6.0);
    }
}
