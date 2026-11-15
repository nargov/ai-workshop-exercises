package com.example.workshop.module1;

import java.util.ArrayList;
import java.util.List;

public class OrderEventBus {

    private final List<OrderEventListener> listeners = new ArrayList<>();

    public void subscribe(OrderEventListener listener) {
        listeners.add(listener);
    }

    public void publish(OrderEvent event) {
        for (OrderEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
