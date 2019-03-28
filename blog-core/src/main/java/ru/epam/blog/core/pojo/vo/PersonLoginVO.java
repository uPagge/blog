package ru.epam.blog.core.pojo.vo;

import java.util.Objects;

public class PersonLoginVO {

    private String login;

    public PersonLoginVO(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonLoginVO)) return false;
        PersonLoginVO that = (PersonLoginVO) o;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
