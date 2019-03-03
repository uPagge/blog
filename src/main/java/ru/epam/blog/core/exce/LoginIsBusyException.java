package ru.epam.blog.core.exce;

public class LoginIsBusyException extends ApiException {
    public LoginIsBusyException(String login) {
        super(0, "Login " + login + "is busy");
    }
}
