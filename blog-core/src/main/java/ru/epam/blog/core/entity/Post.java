package ru.epam.blog.core.entity;

import org.hibernate.annotations.Where;
import ru.epam.blog.core.entity.enums.StatusPost;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "post")
@Access(AccessType.PROPERTY)
public class Post extends Content {

    private String title;
    private String description;
    private String text;
    private Integer views;
    private StatusPost statusPost;
    private List<CommentPost> commentPosts = new ArrayList<>();
    private Person person;
    private Set<Person> likePerson = new HashSet<>();
    private SeoContainer seoContainer;

    @Column(nullable = false)
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

    @Column(nullable = false)
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

    @JoinColumn(nullable = false)
    @Enumerated(EnumType.STRING)
    public StatusPost getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(StatusPost statusPost) {
        this.statusPost = statusPost;
    }

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, targetEntity = CommentPost.class)
    @Where(clause = "content_type = 'POST'")
    public List<CommentPost> getCommentPosts() {
        return commentPosts;
    }

    public void setCommentPosts(List<CommentPost> commentPosts) {
        this.commentPosts = commentPosts;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToMany
    @JoinTable(name = "like_post", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    public Set<Person> getLikePerson() {
        return likePerson;
    }

    public void setLikePerson(Set<Person> likePerson) {
        this.likePerson = likePerson;
    }

    @OneToOne
    @JoinColumn(name = "seo_id")
    public SeoContainer getSeoContainer() {
        return seoContainer;
    }

    public void setSeoContainer(SeoContainer seoContainer) {
        this.seoContainer = seoContainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                Objects.equals(description, post.description) &&
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
        return Objects.hash(super.hashCode(), title, description, text, views, statusPost, commentPosts, person, likePerson, seoContainer);
    }
}
