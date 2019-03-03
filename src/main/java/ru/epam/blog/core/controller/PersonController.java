package ru.epam.blog.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.epam.blog.core.service.PersonService;
import ru.epam.blog.core.utils.ResponseEntityGson;

@RestController
@RequestMapping("api/v1/person")
@PreAuthorize("hasAnyAuthority('USER')")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<String> getInfoPerson() {
        return ResponseEntityGson.getJson(personService.getByLogin(getLogin()), HttpStatus.OK);
    }


    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
