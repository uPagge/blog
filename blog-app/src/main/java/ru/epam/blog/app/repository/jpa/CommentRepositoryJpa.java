package ru.epam.blog.app.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.epam.blog.core.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepositoryJpa extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c INNER JOIN c.post WHERE c.post.id =?1")
    List<Comment> findAllByPostId(Integer postId);

}
