package org.atividade05.model;

public class Category {
    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }
    public void setId(String categoryId) {
        this.id = categoryId;
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

    @Override
    public String toString() {
        return "Category{categoryId=" + id + ", name=" + name + ", description=" + description + "}";
    }
}
