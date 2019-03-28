package ru.epam.blog.core.repository;

import ru.epam.blog.core.entity.Person;

public interface PersonRepository {

    Person add(Person person);

    Person getByLogin(String login);

    Person getByEmail(String email);
}
