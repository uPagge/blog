package ru.epam.blog.app.controller;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.epam.blog.core.pojo.dto.person.RegistrationPersonDTO;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.exce.ApiException;
import ru.epam.blog.core.exce.AuthorizationException;
import ru.epam.blog.core.service.PersonService;
import ru.epam.blog.app.service.auth.TokenAuthenticationService;
import ru.epam.blog.app.utils.ResponseEntityGson;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    public static final Logger log = Logger.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final PersonService personService;
    private final Mapper mapper;

    public AuthController(AuthenticationManager authenticationManager, PersonService personService, Mapper mapper) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.mapper = mapper;
    }

    @ResponseBody
    @PostMapping("login")
    public ResponseEntity<String> generateToken(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            if (authenticationManager.authenticate(authentication).isAuthenticated()) {
                return ResponseEntityGson.getJson(TokenAuthenticationService.addAuthentication(username), HttpStatus.OK);
            } else {
                return ResponseEntityGson.getJson(new AuthorizationException(), HttpStatus.BAD_REQUEST);
            }
        } catch (AuthenticationException e) {
            return ResponseEntityGson.getJson(new AuthorizationException(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationPersonDTO personDTO) {
        try {
            Person person = new Person();
            mapper.map(personDTO, person);
            return ResponseEntityGson.getJson(personService.registration(person), HttpStatus.CREATED);
        } catch (ApiException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.BAD_REQUEST);
        }
    }

}
