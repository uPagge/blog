package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.CommentPost;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public CommentPost add(CommentPost commentPost, Integer postId) throws AccessException {
        Post post = postService.getById(postId);
        if (statusPostValid(post) || userGroupAccess()) {
            commentPost.setContent(post);
            commentPost.setAuthor(authService.getPersonAuth());
            commentPost.setTimeCreate(LocalDateTime.now());
            List<CommentPost> commentPosts = post.getCommentPosts();
            if (commentPosts != null) {
                commentPost.setNumber(commentPosts.size());
            } else {
                commentPost.setNumber(0);
            }
            return commentRepository.save(commentPost);
        } else {
            throw new AccessException();
        }
    }

    @Override
    public List<CommentPost> getAll() {
        return new ArrayList<>(commentRepository.getAll());
    }


    @Override
    public void delete(Integer postId, Integer numberId) {
        CommentPost commentPost = Optional
                .ofNullable(getByPostIdAndNumberId(postId, numberId))
                .orElseThrow(AccessException::new);
        Person personAuth = authService.getPersonAuth();
        if (ownedByUser(commentPost, personAuth) || userGroupAccess()) {
            commentRepository.delete(commentPost.getId());
        } else {
            throw new AccessException();
        }
    }

    @Override
    public CommentPost getByPostIdAndNumberId(Integer postId, Integer commentNumber) {
        return Optional
                .ofNullable(commentRepository.getByPostIdAndCommentNumber(postId, commentNumber))
                .orElseThrow(AccessException::new);
    }

    private boolean ownedByUser(CommentPost commentPost, Person person) {
        return commentPost.getAuthor().getId().equals(person.getId());
    }

    private boolean userGroupAccess() {
        return authService.getPersonAuth().getPersonGroups().contains(PersonGroup.ADMIN);
    }

    private boolean statusPostValid(Post post) {
        return StatusPost.PUBLISHED.equals(post.getStatusPost());
    }


}
