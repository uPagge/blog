package ru.epam.blog.core.service;

import ru.epam.blog.core.domain.Comment;
import ru.epam.blog.core.exce.InvalidBodyException;

import java.util.List;

public interface CommentService {

    Comment add(Comment comment) throws InvalidBodyException;

    List<Comment> getAll();

    List<Comment> getByIdPost(Integer idPost);
}
