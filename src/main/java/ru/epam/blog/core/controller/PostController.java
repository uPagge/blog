package ru.epam.blog.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.StatusPost;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.service.CommentService;
import ru.epam.blog.core.service.PostService;
import ru.epam.blog.core.utils.ResponseEntityGson;

@RestController
@RequestMapping("api/v1/post")
@PreAuthorize("hasAnyAuthority('USER')")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<String> getAllPost() {
        return ResponseEntityGson.getJson(postService.getAllByStatus(StatusPost.PUBLISHED), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Post post) {
        post.setLoginUser(getLogin());
        try {
            return ResponseEntityGson.getJson(postService.created(post), HttpStatus.CREATED);
        } catch (InvalidBodyException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}/comment")
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
