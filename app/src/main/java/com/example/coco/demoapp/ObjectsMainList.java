package com.example.coco.demoapp;

import java.util.List;

/**
 * Created by Coco on 15.11.2017.
 */

public class ObjectsMainList {
    private String WO;
    private String description;
    private String type;
    private int state;
   // private List<ObjectSubdivision> elements;

   /* public List<ObjectSubdivision> getElements() {
        return elements;
    }

    public void setElements(List<ObjectSubdivision> elements) {
        this.elements = elements;
    }
*/
    public int getState() {
        return state;
    }

    public ObjectsMainList(String WO, String description, String type, int state/*, List<ObjectSubdivision> elements)*/) {
        this.WO = WO;
        this.description = description;
        this.type = type;
        this.state=state;
       // this.elements = elements;

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
