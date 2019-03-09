package ru.epam.blog.core.perository.jpa;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.enums.StatusPost;

import java.util.List;

public interface PostRepositoryJpa extends JpaRepository<Post, Integer> {

    List<Post> findAllByStatusPost(StatusPost statusPost);

}
