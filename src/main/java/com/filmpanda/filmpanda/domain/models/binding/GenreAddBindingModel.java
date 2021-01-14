package com.filmpanda.filmpanda.domain.models.binding;

public class GenreAddBindingModel {

    private String name;

    public GenreAddBindingModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
