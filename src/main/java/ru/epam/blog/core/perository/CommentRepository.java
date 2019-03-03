package ru.epam.blog.core.perository;

import ru.epam.blog.core.domain.Comment;

import java.util.Collection;

public interface CommentRepository {

    Comment add(Comment comment);

    Collection<Comment> getAll();

    Collection<Comment> getAllByIdPost(Integer idPost);
}
