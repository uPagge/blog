package ru.epam.blog.core.perository;

import ru.epam.blog.core.entity.Comment;

import java.util.Collection;

public interface CommentRepository {

    Comment save(Comment comment);

    Collection<Comment> getAll();

    Collection<Comment> getAllByIdPost(Integer idPost);

    Comment getById(Integer idCreate);
}
