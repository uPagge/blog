package ru.epam.blog.core.exce;

import com.google.gson.annotations.Expose;

public class ApiException extends Exception {

    @Expose
    private static final String TYPE = "error";

    @Expose
    private final Integer code;

    @Expose
    private final String message;

    @Expose
    private final String description;

    public ApiException(Integer code, String description, String message) {
        this.description = description;
        this.code = code;
        this.message = message;
    }

    public ApiException(Integer code, String message) {
        this(code, "Unknown", message);
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return description + " (" + code + "): " + message;
    }
}
