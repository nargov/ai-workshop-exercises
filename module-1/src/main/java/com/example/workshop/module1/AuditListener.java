package com.example.workshop.module1;

public class AuditListener implements OrderEventListener {

    @Override
    public void onEvent(OrderEvent event) {
        System.out.println("AUDIT " + event);
    }
}
