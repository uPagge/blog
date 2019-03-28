package ru.epam.blog.app.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.epam.blog.core.exception.ApiException;
import ru.epam.blog.core.pojo.vo.ExceptionVO;

@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    private final ConversionService conversionService;

    public MyExceptionHandler(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionVO getApiException(ApiException apiException) {
        return conversionService.convert(apiException, ExceptionVO.class);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionVO getRuntimeException(Exception exceprion) {
        return conversionService.convert(exceprion, ExceptionVO.class);
    }

}
