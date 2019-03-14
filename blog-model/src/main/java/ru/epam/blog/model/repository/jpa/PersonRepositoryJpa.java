package ru.epam.blog.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.epam.blog.core.entity.Person;

public interface PersonRepositoryJpa extends JpaRepository<Person, Integer> {

    Person findByLogin(String login);

}
