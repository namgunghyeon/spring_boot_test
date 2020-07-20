package com.example.demo.exceptions;

public class SocketClientException extends RuntimeException {

    public SocketClientException(String message, Throwable t){
        super(message, t);
    }
}
