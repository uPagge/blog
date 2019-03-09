package ru.epam.blog.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.core.domain.Comment;
import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.enums.StatusPost;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.service.CommentService;
import ru.epam.blog.core.service.PersonService;
import ru.epam.blog.core.service.PostService;
import ru.epam.blog.core.utils.ResponseEntityGson;

@RestController
@RequestMapping("api/v1/comment")
@PreAuthorize("hasAnyAuthority('USER')")
public class CommentController {

    private final CommentService commentService;
    private final PersonService personService;
    private final PostService postService;

    public CommentController(CommentService commentService, PersonService personService, PostService postService) {
        this.commentService = commentService;
        this.personService = personService;
        this.postService = postService;
    }

    @PostMapping("add")
    public ResponseEntity<String> addNewComment(@RequestBody Comment comment) {
        comment.setAuthor(personService.getByLogin(getLogin()));
        try {
            return ResponseEntityGson.getJson(commentService.add(comment), HttpStatus.CREATED);
        } catch (InvalidBodyException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("?postId={id}")
    public ResponseEntity<String> getAllCommentPost(@PathVariable Integer id) {
        Post post = postService.getById(id);
        if (post != null && post.getStatusPost().equals(StatusPost.PUBLISHED)) {
            return ResponseEntityGson.getJson(commentService.getByIdPost(id), HttpStatus.OK);
        } else {
            return ResponseEntityGson.getJson(new InvalidBodyException(), HttpStatus.BAD_REQUEST);
        }
    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
