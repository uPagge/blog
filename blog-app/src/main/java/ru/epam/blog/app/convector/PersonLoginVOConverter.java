package ru.epam.blog.app.convector;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.pojo.vo.PersonLoginVO;

@Component
@Lazy
public class PersonLoginVOConverter implements Converter<Person, PersonLoginVO> {

    @Override
    public PersonLoginVO convert(Person source) {
        return new PersonLoginVO(source.getLogin());
    }

}
