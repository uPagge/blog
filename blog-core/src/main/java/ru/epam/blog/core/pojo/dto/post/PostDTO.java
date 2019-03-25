package ru.epam.blog.core.pojo.dto.post;

import javax.validation.constraints.NotNull;

public class PostDTO {

    @NotNull
    private String title;
    private String description;
    @NotNull
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
