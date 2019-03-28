package ru.epam.blog.core.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.exception.ApiException;
import ru.epam.blog.core.exception.RegistrationExceprion;
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
    public Person registration(Person person) throws ApiException {
        checkLoginAndEmail(person);
        assignDefaultValues(person);
        return personRepository.add(person);
    }

    @Override
    public Boolean check(String login) {
        return personRepository.getByLogin(login) != null;
    }

    @Override
    public Integer getIdByLogin(String login) {
        return personRepository.getByLogin(login).getId();
    }

    private void assignDefaultValues(@NotNull Person person) {
        person.setPersonGroups(Collections.singleton(PersonGroup.USER));
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
    }

    private void checkLoginAndEmail(@NotNull Person person) {
        if (personRepository.getByLogin(person.getLogin()) != null) {
            throw new RegistrationExceprion("Пользователь с таким логином уже зарегистрирован");
        }
        if (personRepository.getByEmail(person.getEmail()) != null) {
            throw new RegistrationExceprion("Пользователь с таким e-mail уже зарегистрирован");
        }
    }
}
