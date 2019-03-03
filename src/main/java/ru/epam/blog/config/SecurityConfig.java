package ru.epam.blog.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.epam.blog.core.proxy.UserDetailsServiceProxy;
import ru.epam.blog.core.service.PersonService;
import ru.epam.blog.core.service.auth.JWTAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final Logger log = Logger.getLogger(SecurityConfig.class);

    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().exceptionHandling()
                .authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_ACCEPTED)))
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests();
    }

    @Autowired
    public void jwtAuthenticationFilter(JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PersonService personService) {
        try {
            auth.userDetailsService(UserDetailsServiceProxy.getProxy(personService));
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Bean
    public AuthenticationManager authManager() throws Exception {
        return this.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
