package ru.epam.blog.model.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.pojo.dto.OffsetAndCount;
import ru.epam.blog.core.repository.PostRepository;
import ru.epam.blog.model.repository.jpa.PostRepositoryJpa;

import java.util.Collection;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostRepositoryJpa postRepositoryJpa;

    public PostRepositoryImpl(PostRepositoryJpa postRepositoryJpa) {
        this.postRepositoryJpa = postRepositoryJpa;
    }

    public Post save(Post post) {
        return postRepositoryJpa.saveAndFlush(post);
    }

    public void delete(Integer id) {
        postRepositoryJpa.deleteById(id);
    }

    public Collection<Post> getAllByStatus(StatusPost statusPost, OffsetAndCount offsetAndCount) {
        return postRepositoryJpa.findAllByStatusPost(statusPost, PageRequest.of(offsetAndCount.getOffset(), offsetAndCount.getCount(), Sort.by("id").ascending()));
    }

    public Post getById(Integer postId) {
        return postRepositoryJpa.findById(postId).orElseThrow(AccessException::new);
    }
}
