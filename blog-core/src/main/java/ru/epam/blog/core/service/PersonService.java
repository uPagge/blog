package ru.epam.blog.core.service;

import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.exce.ApiException;

public interface PersonService {

    Person getByLogin(String login);

    Person registration(Person person) throws ApiException;

    Boolean check(String login);

    Integer getIdByLogin(String login);
}
