package ru.epam.blog.core.pojo.vo;

import java.util.Date;

public class ExceptionVO {

    private String type = "error";
    private String timestamp = new Date().toString();
    private Integer status;
    private String error;
    private String message;

    public ExceptionVO(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
