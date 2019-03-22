package ru.epam.blog.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Сategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer modulId;
    private String name;
    private String description;
    private SeoContainer seoContainer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SeoContainer getSeoContainer() {
        return seoContainer;
    }

    public void setSeoContainer(SeoContainer seoContainer) {
        this.seoContainer = seoContainer;
    }

    public Integer getModulId() {
        return modulId;
    }

    public void setModulId(Integer modulId) {
        this.modulId = modulId;
    }
}
