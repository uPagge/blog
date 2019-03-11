package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exce.AccessException;
import ru.epam.blog.core.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final AuthService authService;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postService, AuthService authService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.authService = authService;
    }


    @Override
    public Comment add(Comment comment, Integer postId) throws AccessException {
        Post post = postService.getById(postId);
        comment.setPost(post);
        comment.setAuthor(authService.getPersonAuth());
        comment.setData(0);
        if (post.getStatusPost().equals(StatusPost.PUBLISHED) || authService.getPersonAuth().getPersonGroups().contains(PersonGroup.ADMIN)) {
            return commentRepository.save(comment);
        } else {
            throw new AccessException();
        }
    }

    @Override
    public List<Comment> getAll() {
        return new ArrayList<>(commentRepository.getAll());
    }

    @Override
    public List<Comment> getByIdPost(Integer idPost) throws AccessException {
        Post post = postService.getById(idPost);
        if (post.getStatusPost().equals(StatusPost.PUBLISHED) ||
                authService.getPersonAuth().getPersonGroups().contains(PersonGroup.ADMIN)) {
            return new ArrayList<>(commentRepository.getAllByIdPost(idPost));
        } else {
            throw new AccessException();
        }
    }

    @Override
    public boolean delete(Integer commentId) throws AccessException {
        Comment comment = commentRepository.getById(commentId);
        Person personAuth = authService.getPersonAuth();
        if (comment.getAuthor().getId().equals(personAuth.getId()) ||
                authService.getPersonAuth().getPersonGroups().contains(PersonGroup.ADMIN)) {
            return true;
        } else {
            throw new AccessException();
        }
    }


}
