package ru.epam.blog.core.exce;

public class AuthorizationException extends ApiException {

    public AuthorizationException() {
        super(13, "Authorization failed", "Wrong login or password");
    }

}
