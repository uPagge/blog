package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.core.entity.Comment;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.pojo.dto.CommentDTO;
import ru.epam.blog.core.pojo.vo.CommentVO;
import ru.epam.blog.core.service.CommentService;
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

    @PostMapping("post/{postId}/comment")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity addNewComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId) throws AccessException {
        Comment comment = new Comment();
        mapper.map(commentDTO, comment);
        Comment commentCreate = commentService.add(comment, postId);
        CommentVO commentVO = new CommentVO();
        mapper.map(commentCreate, commentVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentVO);
    }

    @GetMapping("post/{postId}/comment")
    public ResponseEntity getAllCommentPost(@PathVariable Integer postId) {
        Post post = postService.getById(postId);
        Collection<Comment> comments = post.getComments();
        List<CommentVO> commentVOS = comments.stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            mapper.map(comment, commentVO);
            return commentVO;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(commentVOS);
    }

    @DeleteMapping("comment/{commentId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity deleteComment(@PathVariable Integer commentId) throws AccessException {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }
}
