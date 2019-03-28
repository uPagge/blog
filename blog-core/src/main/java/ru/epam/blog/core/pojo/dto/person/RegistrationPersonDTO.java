package ru.epam.blog.core.pojo.dto.person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegistrationPersonDTO {

    @NotNull(message = "Отсутствует поле login")
    private String login;
    @NotNull(message = "Отсутствует поле password")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}",
            message = "Пароль должен содержать не менее 6 символов" +
                    " (допускаются символы латиницы и цифры)." +
                    " Содержать как минимум одну прописную букву, строчную букву и цифру!")
    private String password;
    @NotNull(message = "Отсуствует поле firstName")
    private String firstName;
    @Email(message = "Неправильный формат e-mal")
    @NotNull(message = "Отсутствует поле email")
    private String email;
    private String lastName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
