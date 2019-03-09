package ru.epam.blog.core.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.epam.blog.core.domain.Person;
import ru.epam.blog.core.domain.enums.PersonGroup;
import ru.epam.blog.core.exce.ApiException;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.exce.LoginIsBusyException;
import ru.epam.blog.core.perository.jpa.PersonRepositoryJpa;

import java.util.Collections;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepositoryJpa personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public PersonServiceImpl(PersonRepositoryJpa personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person getByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    @Override
    public Person registration(Person person) throws ApiException {
        if (this.check(person.getLogin())) {
            if (this.validPerson(person)) {
                person.setPersonGroups(Collections.singleton(PersonGroup.USER));
                person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
                return personRepository.saveAndFlush(person);
            } else {
                throw new InvalidBodyException();
            }
        } else {
            throw new LoginIsBusyException(person.getLogin());
        }
    }

    @Override
    public Boolean check(String login) {
        return personRepository.findByLogin(login) == null;
    }

    @Override
    public Integer getIdByLogin(String login) {
        return personRepository.findByLogin(login).getId();
    }

    private boolean validPerson(Person person) {
        return (person.getPassword() != null && person.getFirstName() != null);
    }
}
