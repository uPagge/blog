package ru.epam.blog.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.epam.blog.core.domain.Person;
import ru.epam.blog.core.exce.ApiException;
import ru.epam.blog.core.exce.AuthorizationException;
import ru.epam.blog.core.service.PersonService;
import ru.epam.blog.core.service.auth.TokenAuthenticationService;
import ru.epam.blog.core.utils.ResponseEntityGson;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PersonService personService;

    public AuthController(AuthenticationManager authenticationManager, PersonService personService) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
    }

    @ResponseBody
    @PostMapping("login")
    public ResponseEntity<String> generateToken(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        if (authenticationManager.authenticate(authentication).isAuthenticated()) {
            return ResponseEntityGson.getJson(TokenAuthenticationService.addAuthentication(username), HttpStatus.OK);
        } else {
            return ResponseEntityGson.getJson(new AuthorizationException(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("registration")
    public ResponseEntity<String> registration(@RequestBody Person person) {
        try {
            return ResponseEntityGson.getJson(personService.registration(person), HttpStatus.CREATED);
        } catch (ApiException e) {
            return ResponseEntityGson.getJson(e, HttpStatus.BAD_REQUEST);
        }
    }

}
