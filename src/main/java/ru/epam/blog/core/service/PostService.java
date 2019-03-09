package ru.epam.blog.core.service;

import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.enums.StatusPost;
import ru.epam.blog.core.exce.InvalidBodyException;

import java.util.List;

public interface PostService {

    Post created(Post post) throws InvalidBodyException;

    boolean remove(Integer id);

    List<Post> getAllByStatus(StatusPost statusPost);

    Post getById(Integer idPost);
}
