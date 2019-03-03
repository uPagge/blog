package ru.epam.blog.core.perository;

import org.springframework.stereotype.Repository;
import ru.epam.blog.core.domain.Comment;

import java.util.*;

@Repository
public class CommentRepositoryMap implements CommentRepository {

    private Map<Integer, Map<Integer, Comment>> comments = new HashMap<>();

    @Override
    public Comment add(Comment comment) {
        if (comments.containsKey(comment.getIdPost())) {
            Map<Integer, Comment> commetsPost = comments.get(comment.getIdPost());
            Integer idCommentInPost = commetsPost.size();
            comment.setId(idCommentInPost);
            commetsPost.put(idCommentInPost, comment);
        } else {
            Map<Integer, Comment> postComments = new HashMap<>();
            comment.setId(0);
            postComments.put(0, comment);
            comments.put(comment.getIdPost(), postComments);
        }
        return comment;
    }

    @Override
    public Collection<Comment> getAll() {
        List<Comment> allComments = new ArrayList<>();
        for (Map<Integer, Comment> comment : comments.values()) {
            allComments.addAll(comment.values());
        }
        return allComments;
    }

    @Override
    public Collection<Comment> getAllByIdPost(Integer idPost) {
        return comments.get(idPost).values();
    }
}
