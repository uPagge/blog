package ru.epam.blog.core.exce;

public class ApiException extends Exception {

    private static final String TYPE = "error";
    private final Integer code;
    private final String message;
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
