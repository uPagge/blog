package ru.epam.blog.core.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "seo")
public class SeoContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 80)
    private String title;
    @Column(length = 140)
    private String description;

    @ElementCollection(targetClass = Set.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "seo_words", joinColumns = {@JoinColumn(name = "id", nullable = false)})
    private Set<String> keyWords;

    @OneToOne(mappedBy = "seoContainer")
    private MyCategory myCategory;

    @OneToOne(mappedBy = "seoContainer")
    private Post post;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
