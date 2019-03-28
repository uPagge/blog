package ru.epam.blog.app.utils;

import org.springframework.validation.BindingResult;
import ru.epam.blog.core.exception.InvalidBodyException;

public class BindResult {

    private BindResult() {
        throw new IllegalStateException();
    }

    public static void getErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> stringBuilder.append(objectError.getDefaultMessage()).append("\n "));
            throw new InvalidBodyException(stringBuilder.toString());
        }
    }

}
