package ru.epam.blog.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.epam.blog.core.domain.Comment;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.service.CommentService;
import ru.epam.blog.core.service.PersonService;
import ru.epam.blog.core.utils.ResponseEntityGson;

@RestController
@RequestMapping("api/v1/comment")
@PreAuthorize("hasAnyAuthority('USER')")
public class CommentController {

    private final CommentService commentService;
    private final PersonService personService;

    public CommentController(CommentService commentService, PersonService personService) {
        this.commentService = commentService;
        this.personService = personService;
    }

    @PostMapping("add")
    public ResponseEntity<String> addNewComment(@RequestBody Comment comment) {
        comment.setIdPerson(personService.getIdByLogin(getLogin()));
        try {
            return ResponseEntityGson.getJson(commentService.add(comment), HttpStatus.CREATED);
        } catch (InvalidBodyException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.CONFLICT);
        }
    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
