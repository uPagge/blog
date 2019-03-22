package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.exception.InvalidBodyException;
import ru.epam.blog.core.pojo.dto.OffsetAndCount;
import ru.epam.blog.core.pojo.post.PostDTO;
import ru.epam.blog.core.pojo.vo.post.PostVO;
import ru.epam.blog.core.service.PostService;

import javax.validation.Valid;
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

    @GetMapping()
    public ResponseEntity<List<PostVO>> getAllPost(@RequestBody @Valid OffsetAndCount offsetAndCount) {
        List<Post> posts = postService.getAllByStatus(StatusPost.PUBLISHED, offsetAndCount);
        List<PostVO> postsVO = posts.stream().map(post1 -> {
            PostVO postVO = new PostVO();
            mapper.map(post1, postVO);
            return postVO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(postsVO, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/delete")
    public void delete(@PathVariable Integer postId) throws AccessException {
        postService.remove(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostVO> getPostInfo(@PathVariable Integer postId) throws AccessException {
        Post post = postService.getById(postId);
        postService.view(post);
        PostVO postVO = new PostVO();
        mapper.map(post, postVO);
        return new ResponseEntity<>(postVO, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<PostVO> create(@RequestBody PostDTO postDTO) throws InvalidBodyException {
        Post post = new Post();
        mapper.map(postDTO, post);
        Post created = postService.created(post);
        PostVO postVO = new PostVO();
        mapper.map(created, postVO);
        return new ResponseEntity<>(postVO, HttpStatus.CREATED);
    }

}
