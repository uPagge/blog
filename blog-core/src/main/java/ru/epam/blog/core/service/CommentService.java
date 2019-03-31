package ru.epam.blog.core.service;

import ru.epam.blog.core.entity.CommentPost;

import java.util.List;

public interface CommentService {

    CommentPost add(CommentPost commentPost, Integer postId);

    List<CommentPost> getAll();

    void delete(Integer postId, Integer commentNumber);

    CommentPost getByPostIdAndNumberId(Integer postId, Integer commentNumber);

}
