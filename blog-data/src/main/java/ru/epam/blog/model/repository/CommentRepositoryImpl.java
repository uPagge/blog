package ru.epam.blog.model.repository;

import org.springframework.stereotype.Repository;
import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.entity.CommentPost;
import ru.epam.blog.core.repository.CommentRepository;
import ru.epam.blog.model.repository.jpa.CommentRepositoryJpa;

import java.util.Collection;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentRepositoryJpa commentRepositoryJpa;

    public CommentRepositoryImpl(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    @Override
    public CommentPost save(CommentPost commentPost) {
        return commentRepositoryJpa.save(commentPost);
    }

    @Override
    public Collection<CommentPost> getAll() {
        return commentRepositoryJpa.findAll();
    }

    @Override
    public Collection<CommentPost> getAllByIdPost(Integer postId) {
        return commentRepositoryJpa.findByPostId(postId);
    }

    @Override
    public void delete(Integer commentId) {
        commentRepositoryJpa.deleteById(commentId);
    }

    @Override
    public CommentPost getByPostIdAndCommentNumber(Integer postId, Integer number) {
        return commentRepositoryJpa.findByPostIdAndNumber(postId, number);
    }

    @Override
    public CommentPost getById(Integer commentId) {
        return commentRepositoryJpa.getOne(commentId);
    }
}
