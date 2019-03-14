package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.app.utils.ResponseEntityGson;
import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.exce.AccessException;
import ru.epam.blog.core.exce.ApiException;
import ru.epam.blog.core.pojo.dto.CommentDTO;
import ru.epam.blog.core.pojo.vo.CommentVO;
import ru.epam.blog.core.service.CommentService;
import ru.epam.blog.core.service.PersonService;
import ru.epam.blog.core.service.PostService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@PreAuthorize("permitAll()")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final Mapper mapper;

    public CommentController(CommentService commentService, PostService postService, Mapper mapper) {
        this.commentService = commentService;
        this.postService = postService;
        this.mapper = mapper;
    }

    @PostMapping("post/{postId}/comment/add")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<String> addNewComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId) {
        Comment comment = new Comment();
        mapper.map(commentDTO, comment);
        try {
            Comment commentCreate = commentService.add(comment, postId);
            mapper.map(commentCreate, commentDTO);
            return ResponseEntityGson.getJson(commentDTO, HttpStatus.CREATED);
        } catch (ApiException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("post/{postId}/comment")
    public ResponseEntity<String> getAllCommentPost(@PathVariable Integer postId) {
        try {
            Post post = postService.getById(postId);
            Collection<Comment> comments = post.getComments();
            List<CommentVO> commentVOS = comments.stream().map(comment -> {
                CommentVO commentVO = new CommentVO();
                mapper.map(comment, commentVO);
                return commentVO;
            }).collect(Collectors.toList());
            return ResponseEntityGson.getJson(commentVOS, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntityGson.getJson(e, HttpStatus.LOCKED);
        }
    }

    @DeleteMapping("comment/{commentId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId) {
        try {
            commentService.delete(commentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AccessException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.CONFLICT);
        }
    }
}
