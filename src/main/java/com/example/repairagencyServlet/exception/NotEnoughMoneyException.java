package com.example.repairagencyServlet.exception;


public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
        super("Not enough for money for payment");
    }
}
