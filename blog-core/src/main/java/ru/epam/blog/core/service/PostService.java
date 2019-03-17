package ru.epam.blog.core.service;

import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.exception.InvalidBodyException;

import java.util.List;

public interface PostService {

    Post created(Post post) throws InvalidBodyException;

    void remove(Integer id) throws AccessException;

    List<Post> getAllByStatus(StatusPost statusPost);

    Post getById(Integer idPost);

    void view(Post post) throws AccessException;

}
