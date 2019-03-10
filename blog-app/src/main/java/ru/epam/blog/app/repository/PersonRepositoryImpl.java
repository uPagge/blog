package ru.epam.blog.app.repository;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.perository.PersonRepository;
import ru.epam.blog.app.repository.jpa.PersonRepositoryJpa;

@Service
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonRepositoryJpa personRepositoryJpa;

    public PersonRepositoryImpl(PersonRepositoryJpa personRepositoryJpa) {
        this.personRepositoryJpa = personRepositoryJpa;
    }

    @Override
    public Person add(Person person) {
        return personRepositoryJpa.saveAndFlush(person);
    }

    @Override
    public Person getByLogin(String login) {
        return personRepositoryJpa.findByLogin(login);
    }
}
