package ru.epam.blog.core.pojo.vo.post;

import java.util.Objects;

public class PostVO {

    private Integer id;
    private String title;
    private String description;
    private String text;
    private Integer views;

    public PostVO(Integer id, String title, String description, String text, Integer views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.text = text;
        this.views = views;
    }

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

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostVO)) return false;
        PostVO postVO = (PostVO) o;
        return Objects.equals(id, postVO.id) &&
                Objects.equals(title, postVO.title) &&
                Objects.equals(description, postVO.description) &&
                Objects.equals(text, postVO.text) &&
                Objects.equals(views, postVO.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, text, views);
    }
}
