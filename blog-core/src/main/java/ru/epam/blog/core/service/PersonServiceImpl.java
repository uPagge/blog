package ru.epam.blog.core.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.exception.ApiException;
import ru.epam.blog.core.exception.InvalidBodyException;
import ru.epam.blog.core.exception.LoginIsBusyException;
import ru.epam.blog.core.repository.PersonRepository;

import javax.validation.constraints.NotNull;
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
    public Person registration(@NotNull Person person) throws ApiException {
        if (checkLoginAndEmail(person)) {
            if (this.validPerson(person)) {
                assignDefaultValues(person);
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

    private void assignDefaultValues(@NotNull Person person) {
        person.setPersonGroups(Collections.singleton(PersonGroup.USER));
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
    }

    private boolean checkLoginAndEmail(@NotNull Person person) {
        return personRepository.getByLogin(person.getLogin())== null && personRepository.getByEmail(person.getEmail()) == null;
    }
}
