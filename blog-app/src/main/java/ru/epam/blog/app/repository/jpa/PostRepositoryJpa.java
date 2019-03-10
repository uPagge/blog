package ru.epam.blog.app.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;

import java.util.List;

@Repository
public interface PostRepositoryJpa extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM  Post p WHERE p.statusPost = ?1")
    List<Post> findAllByStatusPost(StatusPost statusPost);

}
