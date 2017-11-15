package com.example.coco.demoapp;

/**
 * Created by Coco on 15.11.2017.
 */

public class ObjectsMainList {
    private String WO;
    private String description;
    private String type;

    public ObjectsMainList(String WO, String description, String type) {
        this.WO = WO;
        this.description = description;
        this.type = type;
    }

    public String getWO() {
        return WO;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
