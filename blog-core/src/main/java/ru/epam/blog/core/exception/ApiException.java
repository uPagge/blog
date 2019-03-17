package ru.epam.blog.core.exception;

public class ApiException extends Exception {

    private final Integer status;
    private final String error;
    private final String description;

    public ApiException(Integer status, String description, String error) {
        this.description = description;
        this.status = status;
        this.error = error;
    }

    public ApiException(Integer status, String error) {
        this(status, "Unknown", error);
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return description;
    }

    public String getError() {
        return error;
    }
}
