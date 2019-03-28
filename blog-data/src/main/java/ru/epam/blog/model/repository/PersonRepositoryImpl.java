package ru.epam.blog.model.repository;

import org.springframework.stereotype.Repository;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.repository.PersonRepository;
import ru.epam.blog.model.repository.jpa.PersonRepositoryJpa;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonRepositoryJpa personRepositoryJpa;

    public PersonRepositoryImpl(PersonRepositoryJpa personRepositoryJpa) {
        this.personRepositoryJpa = personRepositoryJpa;
    }

    public Person add(Person person) {
        return personRepositoryJpa.saveAndFlush(person);
    }

    public Person getByLogin(String login) {
        return personRepositoryJpa.findByLogin(login);
    }

    @Override
    public Person getByEmail(String email) {
        return personRepositoryJpa.findByEmail(email);
    }
}
