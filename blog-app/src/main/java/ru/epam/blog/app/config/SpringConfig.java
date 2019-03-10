package ru.epam.blog.app.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.epam.blog.app.repository.CommentRepositoryImpl;
import ru.epam.blog.app.repository.PostRepositoryImpl;
import ru.epam.blog.core.perository.PersonRepository;
import ru.epam.blog.core.service.*;

@Configuration
public class SpringConfig {

    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public CommentService commentService(CommentRepositoryImpl commentRepository, AuthService authService, PostService postService) {
        return new CommentServiceImpl(commentRepository, postService, authService);
    }

    @Bean
    public PersonService personService(PersonRepository personRepository) {
        return new PersonServiceImpl(personRepository);
    }

    @Bean
    public PostService postService(AuthService authService, PostRepositoryImpl postRepository) {
        return new PostServiceImpl(authService, postRepository);
    }

}
