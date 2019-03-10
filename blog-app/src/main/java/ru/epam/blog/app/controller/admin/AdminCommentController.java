package ru.epam.blog.app.controller.admin;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.epam.blog.core.service.CommentService;
import ru.epam.blog.app.utils.ResponseEntityGson;

@RestController
@RequestMapping("api/v1/admin/comment")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminCommentController {

    private final CommentService commentService;

    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<String> getAllComment() {
        return ResponseEntityGson.getJson(commentService.getAll(), HttpStatus.OK);
    }
}
