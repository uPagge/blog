package ru.epam.blog.core.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class Content {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "time_create")
    protected LocalDateTime timeCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id) &&
                Objects.equals(timeCreate, content.timeCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeCreate);
    }
}
