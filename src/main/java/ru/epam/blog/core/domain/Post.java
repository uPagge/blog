package ru.epam.blog.core.domain;

import java.util.Objects;

public class Post {

    private Integer id;
    private String title;
    private String description;
    private String text;
    private Integer views;
    private Integer comments;
    private String loginUser;
    private StatusPost statusPost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public StatusPost getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(StatusPost statusPost) {
        this.statusPost = statusPost;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(text, post.text) &&
                Objects.equals(views, post.views) &&
                statusPost == post.statusPost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, views, statusPost);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", views=" + views +
                ", statusPost=" + statusPost +
                '}';
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
