package ru.epam.blog.core.pojo.vo;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentVO {

    private Integer id;
    private Integer postId;
    private Integer authorId;
    private Integer number;
    private String message;
    private LocalDateTime data;

    public CommentVO(Integer id, Integer postId, Integer authorId, Integer number, String message, LocalDateTime data) {
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
        this.number = number;
        this.message = message;
        this.data = data;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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
                Objects.equals(postId, commentVO.postId) &&
                Objects.equals(authorId, commentVO.authorId) &&
                Objects.equals(number, commentVO.number) &&
                Objects.equals(message, commentVO.message) &&
                Objects.equals(data, commentVO.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, authorId, number, message, data);
    }
}
