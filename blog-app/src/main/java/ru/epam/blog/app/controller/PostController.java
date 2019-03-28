package ru.epam.blog.app.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.app.utils.BindResult;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.pojo.dto.OffsetAndCount;
import ru.epam.blog.core.pojo.dto.post.PostDTO;
import ru.epam.blog.core.pojo.vo.PersonLoginVO;
import ru.epam.blog.core.pojo.vo.post.PostVO;
import ru.epam.blog.core.service.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;
    private final ConversionService conversionService;

    public PostController(PostService postService, ConversionService conversionService) {
        this.postService = postService;
        this.conversionService = conversionService;
    }

    @PostMapping("/all")
    public ResponseEntity<List<PostVO>> getAllPost(@RequestBody @Valid OffsetAndCount offsetAndCount, BindingResult bindingResult) {
        BindResult.getErrors(bindingResult);
        List<Post> posts = postService.getAllByStatus(StatusPost.PUBLISHED, offsetAndCount);
        List<PostVO> postsVO = posts.stream()
                .map(post -> conversionService.convert(post, PostVO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(postsVO);
    }

    @DeleteMapping("/{postId}")
    public HttpStatus delete(@PathVariable Integer postId) {
        postService.remove(postId);
        return HttpStatus.OK;
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostVO> getPostInfo(@PathVariable Integer postId) {
        Post post = postService.getById(postId);
        postService.view(post);
        PostVO postVO = conversionService.convert(post, PostVO.class);
        return ResponseEntity.ok(postVO);
    }

    @PostMapping("{postId}/like")
    public HttpStatus likedPost(@PathVariable Integer postId) {
        postService.like(postId);
        return HttpStatus.OK;
    }

    @GetMapping("{postId}/like/person")
    public ResponseEntity<Set<PersonLoginVO>> likePersons(@PathVariable Integer postId) {
        Post post = postService.getById(postId);
        Set<Person> likePerson = post.getLikePerson();
        Set<PersonLoginVO> likePersonsVO = likePerson.stream()
                .map(person -> conversionService.convert(person, PersonLoginVO.class))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(likePersonsVO);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<PostVO> create(@RequestBody @Valid PostDTO postDTO, BindingResult bindingResult) {
        BindResult.getErrors(bindingResult);
        Post post = conversionService.convert(postDTO, Post.class);
        Post createdPost = postService.created(post);
        PostVO postVO = conversionService.convert(createdPost, PostVO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(postVO);
    }

}
