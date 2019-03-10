package ru.epam.blog.core.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.exce.ApiException;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.exce.LoginIsBusyException;
import ru.epam.blog.core.perository.PersonRepository;

import java.util.Collections;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person getByLogin(String login) {
        return personRepository.getByLogin(login);
    }

    @Override
    public Person registration(Person person) throws ApiException {
        if (!this.check(person.getLogin())) {
            if (this.validPerson(person)) {
                person.setPersonGroups(Collections.singleton(PersonGroup.USER));
                person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
                return personRepository.add(person);
            } else {
                throw new InvalidBodyException();
            }
        } else {
            throw new LoginIsBusyException(person.getLogin());
        }
    }

    @Override
    public Boolean check(String login) {
        return personRepository.getByLogin(login) != null;
    }

    @Override
    public Integer getIdByLogin(String login) {
        return personRepository.getByLogin(login).getId();
    }

    private boolean validPerson(Person person) {
        return (person.getPassword() != null && person.getFirstName() != null);
    }
}
