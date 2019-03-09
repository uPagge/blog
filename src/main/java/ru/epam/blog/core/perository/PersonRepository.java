package ru.epam.blog.core.perository;

import ru.epam.blog.core.domain.Person;

public interface PersonRepository {

    Integer add(Person person);

    Boolean checkByLogin(String login);

    Person getByLogin(String login);
}
