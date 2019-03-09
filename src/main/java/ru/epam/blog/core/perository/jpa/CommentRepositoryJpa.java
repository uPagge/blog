package ru.epam.blog.core.perository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.epam.blog.core.domain.Comment;

import java.util.List;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPostId(Integer postId);

}
