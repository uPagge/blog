package ru.epam.blog.core.perository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.epam.blog.core.domain.Person;

public interface PersonRepositoryJpa extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.login = ?1")
    Person findByLogin(String login);

}
