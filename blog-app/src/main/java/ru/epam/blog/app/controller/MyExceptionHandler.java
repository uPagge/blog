package ru.epam.blog.app.controller;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.epam.blog.core.exception.ApiException;
import ru.epam.blog.core.pojo.vo.ExceptionVO;

@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    private final Mapper mapper;

    public MyExceptionHandler(Mapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionVO getApiException(ApiException apiException) {
        ExceptionVO exceptionVO = new ExceptionVO();
        mapper.map(apiException, exceptionVO);
        return exceptionVO;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionVO getRuntimeException(ApiException apiException) {
        ExceptionVO exceptionVO = new ExceptionVO();
        mapper.map(apiException, exceptionVO);
        return exceptionVO;
    }

}
