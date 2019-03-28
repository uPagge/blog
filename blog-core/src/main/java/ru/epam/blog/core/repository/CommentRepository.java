package ru.epam.blog.core.repository;

import ru.epam.blog.core.entity.CommentPost;

import java.util.Collection;

public interface CommentRepository {

    CommentPost save(CommentPost commentPost);

    Collection<CommentPost> getAll();

    Collection<CommentPost> getAllByIdPost(Integer postId);

    void delete(Integer commentId);

    CommentPost getByPostIdAndCommentNumber(Integer postId, Integer number);

    CommentPost getById(Integer commentId);

}
