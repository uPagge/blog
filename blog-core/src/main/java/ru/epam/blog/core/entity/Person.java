package ru.epam.blog.core.entity;

import ru.epam.blog.core.entity.enums.PersonGroup;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Collection<Post> posts;

    @ElementCollection(targetClass = PersonGroup.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "person_group", joinColumns = {@JoinColumn(name = "person_id", nullable = false)})
    @Enumerated(EnumType.STRING)
    @Column(name = "person_group")
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
