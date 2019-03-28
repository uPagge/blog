package ru.epam.blog.core.service;

import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.pojo.dto.OffsetAndCount;

import java.util.List;

public interface PostService {

    Post created(Post post);

    void remove(Integer id);

    List<Post> getAllByStatus(StatusPost statusPost, OffsetAndCount offsetAndCount);

    Post getById(Integer idPost);

    void view(Post post);

    void like(Integer post);

}
