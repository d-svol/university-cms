package com.example.university.customexception;

public class UniversityNotFoundException extends RuntimeException {
    public UniversityNotFoundException(String message) {
        super(message);
    }
}
