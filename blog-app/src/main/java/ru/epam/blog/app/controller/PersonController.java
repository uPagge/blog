package ru.epam.blog.app.controller;

import org.springframework.core.convert.ConversionService;
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
    private final ConversionService conversionService;

    public PersonController(AuthService authService, ConversionService conversionService) {
        this.authService = authService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<PersonVO> getInfoPerson() {
        Person personAuth = authService.getPersonAuth();
        PersonVO personVO = conversionService.convert(personAuth, PersonVO.class);
        return ResponseEntity.ok(personVO);
    }

    @GetMapping("like/post")
    public ResponseEntity<Set<PostVO>> getLikePosts() {
        Person personAuth = authService.getPersonAuth();
        Set<Post> likePost = personAuth.getLikePost();
        Set<PostVO> postsVO = likePost.stream()
                .map(post -> conversionService.convert(post, PostVO.class))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(postsVO);
    }

}
