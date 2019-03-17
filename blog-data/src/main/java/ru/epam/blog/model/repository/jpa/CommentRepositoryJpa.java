package ru.epam.blog.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.epam.blog.core.entity.Comment;

import java.util.List;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Integer> {

    List<Comment> findByPostId(Integer postId);

}
