package com.example.workshop.module1;

public class Mailer {

    String host = "smtp.bookstore.example";
    int port = 25;

    public void send(String to, String body) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        System.out.println("MAIL via " + host + ":" + port + " to " + to + " (" + body.length() + " chars)");
    }
}
