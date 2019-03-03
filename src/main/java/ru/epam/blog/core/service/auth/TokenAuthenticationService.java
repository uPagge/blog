package ru.epam.blog.core.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.epam.blog.core.exce.AuthorizationException;
import ru.epam.blog.core.proxy.UserDetailsProxy;
import ru.epam.blog.core.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenAuthenticationService {

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

    public Authentication getAuthentication(HttpServletRequest request) throws AuthorizationException {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
            if (personService.check(user)) {
                return user != null ? new UsernamePasswordAuthenticationToken(user, null, UserDetailsProxy.getProxy(personService.getByLogin(user)).getAuthorities()) : null;
            } else {
                throw new AuthorizationException();
            }
        }
        return null;
    }

}
