package com.example.weather.exception;

public class LocationByUserIdNotFound extends RuntimeException {
    public LocationByUserIdNotFound(String message) {
        super(message);
    }
}
