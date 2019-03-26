package ru.epam.blog.app.controller;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.epam.blog.app.service.auth.TokenAuthenticationService;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.exception.ApiException;
import ru.epam.blog.core.exception.AuthorizationException;
import ru.epam.blog.core.exception.InvalidBodyException;
import ru.epam.blog.core.pojo.dto.LoginAndPasswordDTO;
import ru.epam.blog.core.pojo.dto.person.RegistrationPersonDTO;
import ru.epam.blog.core.pojo.vo.PersonVO;
import ru.epam.blog.core.service.PersonService;

import javax.validation.Valid;

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
    public ResponseEntity<String> generateToken(@RequestBody LoginAndPasswordDTO loginAndPasswordDTO) throws AuthorizationException {
        String username = loginAndPasswordDTO.getUsername();
        String password = loginAndPasswordDTO.getPassword();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            if (authenticationManager.authenticate(authentication).isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.OK).body(TokenAuthenticationService.addAuthentication(username));
            } else {
                throw new AuthorizationException();
            }
        } catch (AuthenticationException e) {
            throw new AuthorizationException();
        }
    }

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody @Valid RegistrationPersonDTO personDTO, BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InvalidBodyException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Person person = new Person();
        mapper.map(personDTO, person);
        PersonVO personVO = new PersonVO();
        mapper.map(personService.registration(person), personVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personVO);
    }

}
