package ru.epam.blog.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Access(AccessType.PROPERTY)
public class CommentPost extends Comment<Post> {

    @Override
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "content_id")
    public Post getContent() {
        return super.content;
    }

}
