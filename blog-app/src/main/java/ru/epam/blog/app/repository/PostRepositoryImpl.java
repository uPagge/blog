package ru.epam.blog.app.repository;

import org.springframework.stereotype.Service;
import ru.epam.blog.app.repository.jpa.PostRepositoryJpa;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.repository.PostRepository;

import java.util.Collection;

@Service
public class PostRepositoryImpl implements PostRepository {

    private final PostRepositoryJpa postRepositoryJpa;

    public PostRepositoryImpl(PostRepositoryJpa postRepositoryJpa) {
        this.postRepositoryJpa = postRepositoryJpa;
    }

    @Override
    public Post save(Post post) {
        return postRepositoryJpa.saveAndFlush(post);
    }

    @Override
    public boolean remove(Integer id) {
        postRepositoryJpa.delete(postRepositoryJpa.getOne(id));
        return true;
    }

    @Override
    public Collection<Post> getAllByStatus(StatusPost statusPost) {
        return postRepositoryJpa.findAllByStatusPost(statusPost);
    }

    @Override
    public Post getById(Integer postId) {
        return postRepositoryJpa.getOne(postId);
    }
}
