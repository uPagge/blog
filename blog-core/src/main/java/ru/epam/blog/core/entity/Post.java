package ru.epam.blog.core.entity;

import ru.epam.blog.core.entity.enums.StatusPost;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String text;
    private Integer views;
    @JoinColumn(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPost statusPost;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private Person person;

    private SeoContainer seoContainer;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
