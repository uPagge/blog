package ru.epam.blog.core.pojo.vo;

import java.util.Objects;

public class PersonVO {

    private Integer id;
    private String login;
    private String lastName;
    private String firstName;
    private String email;

    public PersonVO(Integer id, String login, String lastName, String firstName, String email) {
        this.id = id;
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVO)) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(id, personVO.id) &&
                Objects.equals(login, personVO.login) &&
                Objects.equals(lastName, personVO.lastName) &&
                Objects.equals(firstName, personVO.firstName) &&
                Objects.equals(email, personVO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, lastName, firstName, email);
    }
}
