package com.example.lab4final.domain;

public class Entity {

    private static final long serialVersionUID = 7331115341259248461L;
    private Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
