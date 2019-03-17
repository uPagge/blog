package ru.epam.blog.core.exception;

public class AccessException extends ApiException {

    public AccessException() {
        super(435, "Access is denied");
    }
}
