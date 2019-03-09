package ru.epam.blog.core.perository;

import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.enums.StatusPost;

import java.util.Collection;

public interface PostRepository {

    Integer add(Post post);

    boolean remove(Integer id);

    Collection<Post> getAllByStatus(StatusPost statusPost);

    Post getById(Integer idPost);
}
