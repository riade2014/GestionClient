package com.mycompagny.client;

public class ClientNotFoundException extends Throwable {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
