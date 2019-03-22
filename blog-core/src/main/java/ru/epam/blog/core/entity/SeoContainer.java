package ru.epam.blog.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class SeoContainer {

    @Column(length = 80)
    private String title;
    @Column(length = 140)
    private String description;
    private Set<String> keyWords;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Set<String> keyWords) {
        this.keyWords = keyWords;
    }
}
