package ru.epam.blog.core.entity;

import ru.epam.blog.core.entity.enums.ContentType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class Comment<C extends Content> {

    protected Integer id;
    protected Person author;
    protected String message;
    protected LocalDateTime timeCreate;
    protected Integer number;
    protected ContentType contentType;
    protected C content;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    public Person getAuthor() {
        return author;
    }

    @Column(name = "message", nullable = false)
    public String getMessage() {
        return message;
    }

    @Column(name = "time_create")
    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    @Column(name = "content_type")
    @Enumerated(EnumType.STRING)
    public ContentType getContentType() {
        return contentType;
    }

    @Transient
    protected abstract C getContent();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public void setContent(C content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(message, comment.message) &&
                Objects.equals(timeCreate, comment.timeCreate) &&
                Objects.equals(number, comment.number) &&
                contentType == comment.contentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, message, timeCreate, number, contentType);
    }
}
