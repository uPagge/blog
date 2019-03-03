package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.domain.Comment;
import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.StatusPost;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.perository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceBean implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentServiceBean(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @Override
    public Comment add(Comment comment) throws InvalidBodyException {
        Post post = postService.getById(comment.getIdPost());
        if (post.getStatusPost().equals(StatusPost.PUBLISHED)) {
            return commentRepository.add(comment);
        } else {
            throw new InvalidBodyException();
        }
    }

    @Override
    public List<Comment> getAll() {
        return new ArrayList<>(commentRepository.getAll());
    }

    @Override
    public List<Comment> getByIdPost(Integer idPost) {
        return new ArrayList<>(commentRepository.getAllByIdPost(idPost));
    }


}
