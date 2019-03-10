package ru.epam.blog.core.perository;

import ru.epam.blog.core.entity.Person;

public interface PersonRepository {

    Person add(Person person);

    Person getByLogin(String login);

}
