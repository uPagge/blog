package ru.epam.blog.core.pojo.dto;

import javax.validation.constraints.NotNull;

public class LoginAndPasswordDTO {

    @NotNull(message = "Поле username не может быть пустым")
    private String username;
    @NotNull(message = "Поле password не может быть пустым")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
