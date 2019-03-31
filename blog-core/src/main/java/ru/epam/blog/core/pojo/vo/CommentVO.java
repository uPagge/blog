package ru.epam.blog.core.pojo.vo;

import ru.epam.blog.core.entity.enums.ContentType;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentVO {

    private Integer id;
    private Integer contentId;
    private Integer authorId;
    private Integer number;
    private String message;
    private LocalDateTime data;
    private ContentType contentType;

    public CommentVO(Integer id, Integer contentId, Integer authorId, Integer number, String message, LocalDateTime data, ContentType contentType) {
        this.id = id;
        this.contentId = contentId;
        this.authorId = authorId;
        this.number = number;
        this.message = message;
        this.data = data;
        this.contentType = contentType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentVO commentVO = (CommentVO) o;
        return Objects.equals(id, commentVO.id) &&
                Objects.equals(contentId, commentVO.contentId) &&
                Objects.equals(authorId, commentVO.authorId) &&
                Objects.equals(number, commentVO.number) &&
                Objects.equals(message, commentVO.message) &&
                Objects.equals(data, commentVO.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentId, authorId, number, message, data);
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
