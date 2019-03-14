package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.app.utils.ResponseEntityGson;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exce.AccessException;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.pojo.dto.PostDTO;
import ru.epam.blog.core.pojo.vo.post.PostVO;
import ru.epam.blog.core.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;
    private final Mapper mapper;

    public PostController(PostService postService, Mapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<String> getAllPost() {
        List<Post> posts = postService.getAllByStatus(StatusPost.PUBLISHED);
        List<PostVO> postsVO = posts.stream().map(post1 -> {
            PostVO postVO = new PostVO();
            mapper.map(post1, postVO);
            return postVO;
        }).collect(Collectors.toList());
        return ResponseEntityGson.getJson(postsVO, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/delete")
    public void delete(@PathVariable Integer postId) {
        postService.remove(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity getPostInfo(@PathVariable Integer postId) {
        Post post = postService.getById(postId);
        try {
            postService.view(post);
            PostVO postVO = new PostVO();
            mapper.map(post, postVO);
            return ResponseEntityGson.getJson(postVO, HttpStatus.OK);
        } catch (AccessException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.LOCKED);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<String> create(@RequestBody PostDTO postDTO) {
        try {
            Post post = new Post();
            mapper.map(postDTO, post);
            Post created = postService.created(post);
            mapper.map(created, postDTO);
            return ResponseEntityGson.getJson(postDTO, HttpStatus.CREATED);
        } catch (InvalidBodyException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.BAD_REQUEST);
        }
    }

}
