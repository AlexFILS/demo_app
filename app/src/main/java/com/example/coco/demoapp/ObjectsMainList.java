package com.example.coco.demoapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Coco on 15.11.2017.
 */

public class ObjectsMainList implements Serializable {
    private String WO;
    private String description;
    private String type;
    private int state;
    private List<String> subelement;


    public String getWO() {
        return WO;
    }

    public void setWO(String WO) {
        this.WO = WO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<String> getSubelement() {
        return subelement;
    }

 public void setSubelement(List<String>  subelement) {
        this.subelement = subelement;
    }

    public ObjectsMainList(String WO, String description, String type, int state,List<String> subelement) {

        this.WO = WO;
        this.description = description;
        this.type = type;
        this.state = state;
        this.subelement = subelement;
    }

    public ObjectsMainList(){

    }
String returnSpecificItem( int index){
    return subelement.get(index);
}

}
