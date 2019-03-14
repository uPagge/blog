package ru.epam.blog.app.repository;

import org.springframework.stereotype.Service;
import ru.epam.blog.app.repository.jpa.CommentRepositoryJpa;
import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.repository.CommentRepository;

import java.util.Collection;

@Service
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentRepositoryJpa commentRepositoryJpa;

    public CommentRepositoryImpl(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepositoryJpa.save(comment);
    }

    @Override
    public Collection<Comment> getAll() {
        return commentRepositoryJpa.findAll();
    }

    @Override
    public Collection<Comment> getAllByIdPost(Integer postId) {
        return commentRepositoryJpa.findAllByPostId(postId);
    }

    @Override
    public Comment getById(Integer commentId) {
        return commentRepositoryJpa.getOne(commentId);
    }

    @Override
    public void delete(Integer id) {
        commentRepositoryJpa.deleteById(id);
    }
}
