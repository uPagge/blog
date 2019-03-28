package ru.epam.blog.core.exception;

public class AuthorizationException extends ApiException {

    public AuthorizationException() {
        super(13, "Authorization failed", "Wrong login or password");
    }

}
