package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.domain.Comment;
import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.enums.StatusPost;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.perository.CommentRepository;
import ru.epam.blog.core.perository.jpa.CommentRepositoryJpa;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepositoryJpa commentRepository;
    private final PostService postService;

    public CommentServiceImpl(CommentRepositoryJpa commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @Override
    public Comment add(Comment comment) throws InvalidBodyException {
        Post post = postService.getById(comment.getPost().getId());
        if (post.getStatusPost().equals(StatusPost.PUBLISHED)) {
            return commentRepository.saveAndFlush(comment);
        } else {
            throw new InvalidBodyException();
        }
    }

    @Override
    public List<Comment> getAll() {
        return new ArrayList<>(commentRepository.findAll());
    }

    @Override
    public List<Comment> getByIdPost(Integer idPost) {
        return new ArrayList<>(commentRepository.findAllByPostId(idPost));
    }


}
