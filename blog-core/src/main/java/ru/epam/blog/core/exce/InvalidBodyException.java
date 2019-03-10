package ru.epam.blog.core.exce;

public class InvalidBodyException extends ApiException {

    public InvalidBodyException() {
        super(1, "Invalid request body");
    }
}
