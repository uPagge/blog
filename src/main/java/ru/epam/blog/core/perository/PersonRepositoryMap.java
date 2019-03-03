package ru.epam.blog.core.perository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.epam.blog.core.domain.Person;
import ru.epam.blog.core.domain.PersonGroup;

import java.util.*;

@Repository
public class PersonRepositoryMap implements PersonRepository {

    private Map<String, Person> personMap = new HashMap<>();

    private Integer count = 0;

    public PersonRepositoryMap() {
        Person person = new Person();
        person.setFirstName("Mark");
        person.setLastName("Struchkov");
        person.setLogin("root");
        Set<PersonGroup> groups = new HashSet<>();
        groups.add(PersonGroup.ADMIN);
        groups.add(PersonGroup.USER);
        person.setPersonGroups(groups);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        person.setPassword(bCryptPasswordEncoder.encode("adminpass"));
        add(person);
    }

    @Override
    public Person add(Person person) {
        person.setId(count++);
        personMap.put(person.getLogin(), person);
        return person;
    }

    @Override
    public Boolean checkByLogin(String login) {
        return personMap.containsKey(login);
    }

    @Override
    public Person getByLogin(String login) {
        return personMap.get(login);
    }
}
