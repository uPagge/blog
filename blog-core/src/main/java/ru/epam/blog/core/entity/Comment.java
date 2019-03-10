package ru.epam.blog.core.entity;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Person author;

    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private Integer data;

    public Comment() {

    }

    public Comment(Post post, Person author, String message, Integer data) {
        this.post = post;
        this.author = author;
        this.message = message;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
