package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.repository.CommentRepository;

import java.time.LocalDateTime;
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
        if (post != null) {
            if (statusPostValid(post) || userGroupAccess()) {
                comment.setPost(post);
                comment.setAuthor(authService.getPersonAuth());
                comment.setTimeCreate(LocalDateTime.now());
                return commentRepository.save(comment);
            } else {
                throw new AccessException();
            }
        } else {
            throw new AccessException();
        }
    }

    @Override
    public List<Comment> getAll() {
        return new ArrayList<>(commentRepository.getAll());
    }

    @Override
    public List<Comment> getAllByIdPost(Integer idPost) throws AccessException {
        Post post = postService.getById(idPost);
        if (post != null) {
            if (statusPostValid(post) || userGroupAccess()) {
                return new ArrayList<>(commentRepository.getAllByIdPost(idPost));
            } else {
                throw new AccessException();
            }
        } else {
            throw new AccessException();
        }
    }

    @Override
    public boolean delete(Integer commentId) throws AccessException {
        Comment comment = commentRepository.getById(commentId);
        if (comment != null) {
            Person personAuth = authService.getPersonAuth();
            if (ownedByUser(comment, personAuth) || userGroupAccess()) {
                commentRepository.delete(commentId);
                return true;
            } else {
                throw new AccessException();
            }
        } else {
            throw new AccessException();
        }
    }

    private boolean ownedByUser(Comment comment, Person person) {
        return comment.getAuthor().getId().equals(person.getId());
    }

    private boolean userGroupAccess() {
        return authService.getPersonAuth().getPersonGroups().contains(PersonGroup.ADMIN);
    }

    private boolean statusPostValid(Post post) {
        return StatusPost.PUBLISHED.equals(post.getStatusPost());
    }


}
