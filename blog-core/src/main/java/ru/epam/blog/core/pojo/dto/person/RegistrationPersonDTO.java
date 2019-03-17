package ru.epam.blog.core.pojo.dto.person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RegistrationPersonDTO {

    @NotNull(message = "Отсутствует поле login")
    private String login;
    @NotNull(message = "Отсутствует поле password")
    private String password;
    @NotNull(message = "Отсуствует поле firstName")
    private String firstName;
    @Email @NotNull(message = "Отсутствует, или не валидно, поле email")
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
