package ru.epam.blog.app.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
<<<<<<< HEAD:blog-app/src/main/java/ru/epam/blog/app/service/auth/TokenAuthenticationService.java
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.epam.blog.app.proxy.UserDetailsProxy;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.service.AuthService;
=======
<<<<<<< HEAD:src/main/java/ru/epam/blog/core/service/auth/TokenAuthenticationService.java
import org.springframework.stereotype.Service;
import ru.epam.blog.core.exce.AuthorizationException;
import ru.epam.blog.core.proxy.UserDetailsProxy;
=======
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.epam.blog.app.proxy.UserDetailsProxy;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.service.AuthService;
>>>>>>> Посты видят и неавторизованные:blog-app/src/main/java/ru/epam/blog/app/service/auth/TokenAuthenticationService.java
>>>>>>> Посты видят и неавторизованные:src/main/java/ru/epam/blog/core/service/auth/TokenAuthenticationService.java
import ru.epam.blog.core.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

@Service
public class TokenAuthenticationService implements AuthService {

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    private static final String SECRET = "ThisIsASecret";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    private PersonService personService;

    public TokenAuthenticationService(PersonService personService) {
        this.personService = personService;
    }

    public static String addAuthentication(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
<<<<<<< HEAD:blog-app/src/main/java/ru/epam/blog/app/service/auth/TokenAuthenticationService.java
            if (user != null && personService.check(user)) {
                return new UsernamePasswordAuthenticationToken(user, null, UserDetailsProxy.getProxy(personService.getByLogin(user)).getAuthorities());
=======
<<<<<<< HEAD:src/main/java/ru/epam/blog/core/service/auth/TokenAuthenticationService.java
            if (personService.check(user)) {
                return user != null ? new UsernamePasswordAuthenticationToken(user, null, UserDetailsProxy.getProxy(personService.getByLogin(user)).getAuthorities()) : null;
            } else {
                throw new AuthorizationException();
=======
            if (user != null && personService.check(user)) {
                return new UsernamePasswordAuthenticationToken(user, null, UserDetailsProxy.getProxy(personService.getByLogin(user)).getAuthorities());
>>>>>>> Посты видят и неавторизованные:blog-app/src/main/java/ru/epam/blog/app/service/auth/TokenAuthenticationService.java
>>>>>>> Посты видят и неавторизованные:src/main/java/ru/epam/blog/core/service/auth/TokenAuthenticationService.java
            }
        }
        return null;
//        return new UsernamePasswordAuthenticationToken("anonymousUser", null, Collections.singleton(new SimpleGrantedAuthority("ANONYMOUS")));
    }

<<<<<<< HEAD:blog-app/src/main/java/ru/epam/blog/app/service/auth/TokenAuthenticationService.java
=======
<<<<<<< HEAD:src/main/java/ru/epam/blog/core/service/auth/TokenAuthenticationService.java
=======
>>>>>>> Посты видят и неавторизованные:src/main/java/ru/epam/blog/core/service/auth/TokenAuthenticationService.java
    public Person getPersonAuth() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            return personService.getByLogin(login);
        } else {
            return null;
        }
    }

>>>>>>> Посты видят и неавторизованные:blog-app/src/main/java/ru/epam/blog/app/service/auth/TokenAuthenticationService.java
}
