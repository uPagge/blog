package ru.epam.blog.model.repository.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;

import java.util.List;

public interface PostRepositoryJpa extends JpaRepository<Post, Integer> {

    List<Post> findAllByStatusPost(StatusPost statusPost, Pageable pageable);

}
