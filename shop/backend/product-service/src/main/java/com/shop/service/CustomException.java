package com.shop.service;

public class CustomException extends Exception {
    public String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
