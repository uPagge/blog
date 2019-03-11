package ru.epam.blog.core.service;

import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exce.AccessException;
import ru.epam.blog.core.exce.InvalidBodyException;

import java.util.List;

public interface PostService {

    Post created(Post post) throws InvalidBodyException;

    boolean remove(Integer id);

    List<Post> getAllByStatus(StatusPost statusPost);

    Post getById(Integer idPost);

    void view(Post post) throws AccessException;

}
