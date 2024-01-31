package com.example.university.customexception;

public class ProfessorNotFoundException extends RuntimeException {

    public ProfessorNotFoundException(String message) {
        super(message);
    }

}
