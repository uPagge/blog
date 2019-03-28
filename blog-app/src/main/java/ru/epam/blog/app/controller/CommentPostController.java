package ru.epam.blog.app.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.app.utils.BindResult;
import ru.epam.blog.core.entity.CommentPost;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.pojo.dto.CommentDTO;
import ru.epam.blog.core.pojo.vo.CommentVO;
import ru.epam.blog.core.service.CommentService;
import ru.epam.blog.core.service.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/post")
public class CommentPostController {

    private final PostService postService;
    private final ConversionService conversionService;
    private final CommentService commentService;

    public CommentPostController(PostService postService, ConversionService conversionService, CommentService commentService) {
        this.postService = postService;
        this.conversionService = conversionService;
        this.commentService = commentService;
    }

    @GetMapping("{postId}/comment")
    public ResponseEntity<Set<CommentVO>> getAllCommentsByPost(@PathVariable Integer postId) {
        List<CommentPost> commentPosts = postService.getById(postId).getCommentPosts();
        Set<CommentVO> commentVOS = commentPosts.stream()
                .map(comment -> conversionService.convert(comment, CommentVO.class))
                .collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentVOS);
    }

    @PostMapping("{postId}/comment")
    public ResponseEntity<CommentVO> addNewComment(@PathVariable Integer postId,
                                                   @RequestBody @Valid CommentDTO commentDTO,
                                                   BindingResult bindingResult) {
        BindResult.getErrors(bindingResult);
        CommentPost commentPost = commentService.add(conversionService.convert(commentDTO, CommentPost.class), postId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversionService.convert(commentPost, CommentVO.class));
    }

    @GetMapping("{postId}/comment/{commentId}")
    public ResponseEntity<CommentVO> getCommentsByPostIdAndNumberComment(@PathVariable Integer postId,
                                                                         @PathVariable Integer commentId) {
        Post post = postService.getById(postId);
        CommentPost commentPost = post.getCommentPosts().parallelStream()
                .filter(commentSearch -> commentSearch.getNumber().equals(commentId))
                .findFirst()
                .orElseThrow(AccessException::new);
        return ResponseEntity.ok(conversionService.convert(commentPost, CommentVO.class));
    }

    @DeleteMapping("{postId}/comment/{commentId}")
    public HttpStatus deleteOnComment(@PathVariable Integer postId,
                                      @PathVariable Integer commentId) {
        commentService.delete(postId, commentId);
        return HttpStatus.OK;
    }
}
