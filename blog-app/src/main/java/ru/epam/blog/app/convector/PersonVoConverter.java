package ru.epam.blog.app.convector;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.pojo.vo.PersonVO;

@Component
@Lazy
public class PersonVoConverter implements Converter<Person, PersonVO> {

    @Override
    public PersonVO convert(Person source) {
        return new PersonVO(
                source.getId(),
                source.getLogin(),
                source.getLastName(),
                source.getFirstName(),
                source.getEmail()
        );
    }
}
