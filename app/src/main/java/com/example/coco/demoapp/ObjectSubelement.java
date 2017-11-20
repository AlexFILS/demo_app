package com.example.coco.demoapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Coco on 16.11.2017.
 */

public class ObjectSubelement implements Serializable {
    private String name;
    private Map<String,List<String>> elements;

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<String>> getElements() {
        return elements;
    }

    public void setElements(Map<String, List<String>> elements) {
        this.elements = elements;
    }

    public ObjectSubelement(){

    }

    public ObjectSubelement(String name, Map<String, List<String>> elements) {

        this.name = name;
        this.elements = elements;
    }


    public String getName() {
        return name;
    }

  /*  public String getElementName(Map<String, List<String>> selects,int i){
        String key=null;
        for(Map.Entry<String, List<String>> entry : selects.entrySet()) {
            if()
                key = entry.getKey();

        }

        return key;
    }
*/

}
