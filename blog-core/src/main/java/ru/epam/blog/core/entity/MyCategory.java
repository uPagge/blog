package ru.epam.blog.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class MyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "module_id")
    private Integer moduleId;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "category_seo",
            joinColumns = @JoinColumn(name="cat_id"),
            inverseJoinColumns = @JoinColumn(name="seo_id")
    )
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

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
