package ru.epam.blog.core.pojo.dto;

import javax.validation.constraints.NotNull;

public class CommentDTO {

    @NotNull
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
