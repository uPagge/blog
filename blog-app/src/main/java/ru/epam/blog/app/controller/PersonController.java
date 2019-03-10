package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.epam.blog.app.utils.ResponseEntityGson;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.service.AuthService;
import ru.epam.blog.core.pojo.vo.PersonVO;

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
    public ResponseEntity<String> getInfoPerson() {
        Person personAuth = authService.getPersonAuth();
        PersonVO personVO = new PersonVO();
        mapper.map(personAuth, personVO);
        return ResponseEntityGson.getJson(authService.getPersonAuth(), HttpStatus.OK);
    }

}
