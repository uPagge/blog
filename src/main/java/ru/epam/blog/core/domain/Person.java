package ru.epam.blog.core.domain;

import ru.epam.blog.core.domain.enums.PersonGroup;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Collection<Post> posts;

    @ElementCollection(targetClass = PersonGroup.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "person_group", joinColumns = @JoinColumn(name = "person_id", nullable = false))
    @Enumerated(EnumType.STRING)
    private Set<PersonGroup> personGroups;

    public Person(String login, String firstName, String lastName, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Person() {

    }

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

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}
