package ru.epam.blog.core.exception;

public class InvalidBodyException extends ApiException {

    public InvalidBodyException() {
        super(1, "Invalid request body");
    }

    public InvalidBodyException(String message) {
        super(1, message,  "Invalid request body");
    }
}
