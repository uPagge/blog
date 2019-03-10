package ru.epam.blog.app.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.epam.blog.core.entity.Person;

@Repository
public interface PersonRepositoryJpa extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.login = ?1")
    Person findByLogin(String login);

}
