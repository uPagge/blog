package ru.epam.blog.core.service;

import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.entity.CommentPost;

import java.util.List;

public interface CommentService {

    CommentPost add(CommentPost commentPost, Integer postId);

    List<CommentPost> getAll();

    void delete(Integer postId, Integer commentNumber);

    Comment getByPostIdAndNumberId(Integer postId, Integer commentNumber);

}
