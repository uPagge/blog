package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.pojo.vo.PersonVO;
import ru.epam.blog.core.pojo.vo.post.PostVO;
import ru.epam.blog.core.service.AuthService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/person")
@PreAuthorize("hasAnyAuthority('USER')")
public class PersonController {

    private final AuthService authService;
    private final Mapper mapper;

    public PersonController(AuthService authService, Mapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity getInfoPerson() {
        Person personAuth = authService.getPersonAuth();
        PersonVO personVO = new PersonVO();
        mapper.map(personAuth, personVO);
        return ResponseEntity.status(HttpStatus.OK).body(personVO);
    }

    @GetMapping("like/post")
    public ResponseEntity getLikePosts() {
        Person personAuth = authService.getPersonAuth();
        Set<Post> likePost = personAuth.getLikePost();
        Set<PostVO> postsVO = likePost.stream().map(post1 -> {
            PostVO postVO = new PostVO();
            mapper.map(post1, postVO);
            return postVO;
        }).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.OK).body(postsVO);
    }

}
