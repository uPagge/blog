package ru.epam.blog.core.utils;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityGson {

    private static final Gson gson = new Gson();

    private ResponseEntityGson() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseEntity<String> getJson(Object object, HttpStatus httpStatus) {
        return new ResponseEntity<>(gson.toJson(object), httpStatus);
    }

    public static ResponseEntity<String> getJson(HttpStatus httpStatus) {
        return new ResponseEntity<>(httpStatus);
    }

}
