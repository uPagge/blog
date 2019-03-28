package ru.epam.blog.app.convector.exception;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.pojo.vo.ExceptionVO;

@Component
@Lazy
public class ExceptionInExceptionVOConverter implements Converter<Exception, ExceptionVO> {
    @Override
    public ExceptionVO convert(Exception source) {
        return new ExceptionVO(666, source.getMessage(), "Other Errors");
    }
}
