package ru.epam.blog.app.convector.exception;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.exception.ApiException;
import ru.epam.blog.core.pojo.vo.ExceptionVO;

@Component
@Lazy
public class ExceptionVOConverter implements Converter<ApiException, ExceptionVO> {

    @Override
    public ExceptionVO convert(ApiException source) {
        return new ExceptionVO(
                source.getStatus(),
                source.getError(),
                source.getMessage()
        );
    }

}
