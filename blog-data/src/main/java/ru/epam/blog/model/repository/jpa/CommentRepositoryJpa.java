package ru.epam.blog.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.epam.blog.core.entity.CommentPost;

import java.util.List;

public interface CommentRepositoryJpa extends JpaRepository<CommentPost, Integer> {

    List<CommentPost> findByContentId(Integer contentId);

    CommentPost findByContentIdAndNumber(Integer contentId, Integer number);

}
