package ru.epam.blog.core.perository;

import ru.epam.blog.core.domain.Comment;
import ru.epam.blog.core.domain.Post;

import java.util.Collection;

public interface CommentRepository {

    Integer add(Comment comment);

    Collection<Comment> getAll();

    Collection<Comment> getAllByIdPost(Integer idPost);

    Comment getById(Integer idCreate);
}
