package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.exception.ApiException;
import ru.epam.blog.core.pojo.dto.OffsetAndCount;
import ru.epam.blog.core.pojo.post.PostDTO;
import ru.epam.blog.core.pojo.vo.PersonMinVO;
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
    private final Mapper mapper;

    public PostController(PostService postService, Mapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity getAllPost(@RequestBody @Valid OffsetAndCount offsetAndCount) {
        List<Post> posts = postService.getAllByStatus(StatusPost.PUBLISHED, offsetAndCount);
        List<PostVO> postsVO = posts.stream().map(post1 -> {
            PostVO postVO = new PostVO();
            mapper.map(post1, postVO);
            return postVO;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(postsVO);
    }

    @DeleteMapping("/{postId}/delete")
    public void delete(@PathVariable Integer postId) throws AccessException {
        postService.remove(postId);
    }

    @GetMapping("{postId}")
    public ResponseEntity getPostInfo(@PathVariable Integer postId) throws AccessException {
        Post post = postService.getById(postId);
        postService.view(post);
        PostVO postVO = new PostVO();
        mapper.map(post, postVO);
        return ResponseEntity.status(HttpStatus.OK).body(postVO);
    }

    @PostMapping("{postId}/like")
    public ResponseEntity likedPost(@PathVariable Integer postId) throws ApiException {
        Post post = postService.getById(postId);
        try {
            postService.like(post);
            return ResponseEntity.ok().build();
        } catch (AccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{postId}/like/person")
    public ResponseEntity likePersons(@PathVariable Integer postId) throws ApiException {
        Post post = postService.getById(postId);
        Set<Person> likePerson = post.getLikePerson();
        Set<PersonMinVO> likePersonsVO = likePerson.stream().map(person -> {
            PersonMinVO personMinVO = new PersonMinVO();
            mapper.map(person, personMinVO);
            return personMinVO;
        }).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.OK).body(likePersonsVO);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity create(@RequestBody PostDTO postDTO) throws ApiException {
        Post post = new Post();
        mapper.map(postDTO, post);
        Post created = postService.created(post);
        PostVO postVO = new PostVO();
        mapper.map(created, postVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postVO);
    }

}
