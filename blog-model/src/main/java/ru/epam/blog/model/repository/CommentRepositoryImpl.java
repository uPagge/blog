package ru.epam.blog.model.repository;

import org.springframework.stereotype.Repository;
import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.repository.CommentRepository;
import ru.epam.blog.model.repository.jpa.CommentRepositoryJpa;

import java.util.Collection;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentRepositoryJpa commentRepositoryJpa;

    public CommentRepositoryImpl(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    public Comment save(Comment comment) {
        return commentRepositoryJpa.save(comment);
    }

    public Collection<Comment> getAll() {
        return commentRepositoryJpa.findAll();
    }

    public Collection<Comment> getAllByIdPost(Integer postId) {
        return commentRepositoryJpa.findByPostId(postId);
    }

    public Comment getById(Integer commentId) {
        return commentRepositoryJpa.getOne(commentId);
    }

    public void delete(Integer id) {
        commentRepositoryJpa.deleteById(id);
    }
}
