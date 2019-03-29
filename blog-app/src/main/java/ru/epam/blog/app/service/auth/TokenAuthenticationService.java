package ru.epam.blog.app.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.epam.blog.app.proxy.UserDetailsProxy;
import ru.epam.blog.app.vo.Token;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.service.AuthService;
import ru.epam.blog.core.service.PersonService;

import javax.servlet.http.HttpServletRequest;
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

    public static Token addAuthentication(String username) {
        String tokenKey = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return new Token(tokenKey, "bearer");
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
            if (user != null && personService.check(user)) {
                return new UsernamePasswordAuthenticationToken(user, null, UserDetailsProxy.getProxy(personService.getByLogin(user)).getAuthorities());
            }
        }
        return null;
    }

    public Person getPersonAuth() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return personService.getByLogin(login);
    }

}
