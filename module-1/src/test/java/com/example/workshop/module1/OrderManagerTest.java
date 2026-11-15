package com.example.workshop.module1;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class OrderManagerTest {

    @Test
    public void processesOrderCorrectly() {
        OrderManager manager = new OrderManager();

        String result = manager.proc(
                "carol",
                new String[] {"Clean Code"},
                new int[] {2},
                new double[] {39.9},
                "R",
                null,
                false);

        assertThat(result).isNotNull();
        assertThat(result.length()).isGreaterThan(0);
    }
}
