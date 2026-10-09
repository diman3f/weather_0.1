package com.example.weather.exception;

public class DataBaseException extends RuntimeException {

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
