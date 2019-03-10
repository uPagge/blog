package ru.epam.blog.core.exce;

public class AccessException extends ApiException {

    public AccessException() {
        super(435, "Access is denied");
    }
}
