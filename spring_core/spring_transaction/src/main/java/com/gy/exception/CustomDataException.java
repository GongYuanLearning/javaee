package com.gy.exception;

public class CustomDataException extends Exception {
    public CustomDataException(String message) {
        super(message);
    }

    public CustomDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
