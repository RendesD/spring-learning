package com.david.mainservice.exceptions;

public class BookIdMismatchException extends RuntimeException {
    public BookIdMismatchException(String message) {
        super(message);
    }
}
