package ru.epam.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD:blog-app/src/main/java/ru/epam/blog/app/BlogApplication.java
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
=======
<<<<<<< HEAD:src/main/java/ru/epam/blog/BlogApplication.java
=======
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
>>>>>>> Посты видят и неавторизованные:blog-app/src/main/java/ru/epam/blog/app/BlogApplication.java
>>>>>>> Посты видят и неавторизованные:src/main/java/ru/epam/blog/BlogApplication.java
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
<<<<<<< HEAD:blog-app/src/main/java/ru/epam/blog/app/BlogApplication.java
@EntityScan(basePackages = "ru.epam.blog.core.entity")
@ComponentScan({"ru.epam.blog.core.service", "ru.epam.blog.app"})
=======
<<<<<<< HEAD:src/main/java/ru/epam/blog/BlogApplication.java
=======
@EntityScan(basePackages = "ru.epam.blog.core.entity")
@ComponentScan({"ru.epam.blog.core.service", "ru.epam.blog.app"})
>>>>>>> Посты видят и неавторизованные:blog-app/src/main/java/ru/epam/blog/app/BlogApplication.java
>>>>>>> Посты видят и неавторизованные:src/main/java/ru/epam/blog/BlogApplication.java
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
