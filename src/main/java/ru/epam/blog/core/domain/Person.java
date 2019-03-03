package ru.epam.blog.core.domain;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class Person {

    @Expose
    private Integer id;
    @Expose
    private String login;
    @Expose
    private String lastName;
    @Expose
    private String firstName;
    private String password;
    @Expose
    private Set<PersonGroup> personGroups;

    public Set<PersonGroup> getPersonGroups() {
        return personGroups;
    }

    public void setPersonGroups(Set<PersonGroup> personGroups) {
        this.personGroups = personGroups;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
