package com.example.workshop.module1;

import java.util.HashMap;
import java.util.Map;

public class RoundingStrategyFactory {

    private static final RoundingStrategyFactory INSTANCE = new RoundingStrategyFactory();
    private static final String DEFAULT = "HALF_UP";

    private final Map<String, RoundingStrategy> registry = new HashMap<>();

    private RoundingStrategyFactory() {
        registry.put(DEFAULT, new HalfUpRoundingStrategy());
    }

    public static RoundingStrategyFactory getInstance() {
        return INSTANCE;
    }

    public RoundingStrategy get() {
        return registry.get(DEFAULT);
    }
}
