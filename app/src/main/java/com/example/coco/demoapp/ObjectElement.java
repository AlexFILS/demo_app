package com.example.coco.demoapp;

/**
 * Created by Coco on 16.11.2017.
 */

public class ObjectElement {
    private String name;
    private String description;

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

    public ObjectElement(String name, String description) {

        this.name = name;
        this.description = description;
    }
}
