package ru.epam.blog.core.service;

import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.exce.AccessException;

import java.util.List;

public interface CommentService {

    Comment add(Comment comment, Integer postId) throws AccessException;

    List<Comment> getAll();

    List<Comment> getByIdPost(Integer idPost) throws AccessException;

    boolean delete(Integer commentId) throws AccessException;
}
