package ru.epam.blog.app.convector;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.pojo.dto.person.RegistrationPersonDTO;

@Component
@Lazy
public class RegistrationPersonDTOInPersonConverter implements Converter<RegistrationPersonDTO, Person> {

    @Override
    public Person convert(RegistrationPersonDTO source) {
        Person person = new Person();
        person.setPassword(source.getPassword());
        person.setEmail(source.getEmail());
        person.setFirstName(source.getFirstName());
        person.setLastName(source.getLastName());
        person.setLogin(source.getLogin());
        return person;
    }
}
