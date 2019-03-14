package ru.epam.blog.core.repository;

import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;

import java.util.Collection;

public interface PostRepository {

    Post save(Post post);

    void delete(Integer id);

    Collection<Post> getAllByStatus(StatusPost statusPost);

    Post getById(Integer idPost);
}
