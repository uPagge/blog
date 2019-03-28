package ru.epam.blog.core.entity;

import ru.epam.blog.core.entity.enums.StatusPost;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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

    @Column(name = "time_create")
    private LocalDateTime timeCreate;

    @Column(nullable = false)
    private String text;
    private Integer views;
    @JoinColumn(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPost statusPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentPost> commentPosts = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private Person person;

    @ManyToMany
    @JoinTable(name = "like_post", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> likePerson = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "seo_id")
    private SeoContainer seoContainer;

    public Set<Person> getLikePerson() {
        return likePerson;
    }

    public void setLikePerson(Set<Person> likePerson) {
        this.likePerson = likePerson;
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

    public SeoContainer getSeoContainer() {
        return seoContainer;
    }

    public void setSeoContainer(SeoContainer seoContainer) {
        this.seoContainer = seoContainer;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    public List<CommentPost> getCommentPosts() {
        return commentPosts;
    }

    public void setCommentPosts(List<CommentPost> commentPosts) {
        this.commentPosts = commentPosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(description, post.description) &&
                Objects.equals(timeCreate, post.timeCreate) &&
                Objects.equals(text, post.text) &&
                Objects.equals(views, post.views) &&
                statusPost == post.statusPost &&
                Objects.equals(commentPosts, post.commentPosts) &&
                Objects.equals(person, post.person) &&
                Objects.equals(likePerson, post.likePerson) &&
                Objects.equals(seoContainer, post.seoContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, timeCreate, text, views, statusPost, commentPosts, person, likePerson, seoContainer);
    }
}
