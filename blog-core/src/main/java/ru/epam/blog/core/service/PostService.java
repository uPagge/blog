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
<<<<<<< HEAD:blog-core/src/main/java/ru/epam/blog/core/service/PostService.java

    void view(Post post) throws AccessException;

=======
<<<<<<< HEAD:src/main/java/ru/epam/blog/core/service/PostService.java
=======

    void view(Post post) throws AccessException;

>>>>>>> Посты видят и неавторизованные:blog-core/src/main/java/ru/epam/blog/core/service/PostService.java
>>>>>>> Посты видят и неавторизованные:src/main/java/ru/epam/blog/core/service/PostService.java
}
