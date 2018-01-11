package com.example.coco.demoapp.Model;

import android.util.Log;

import com.couchbase.lite.Database;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alexandrumihai1 on 04/01/2018.
 */

public class WoOverview implements Serializable{
    private String _id;
    private String price;
    private String type;
    private String _rev;
    private String description;
    private String status;
    @JsonProperty("materials")
    private List<Object> materials = new ArrayList<Object>();
    @JsonProperty("ob_info")
    private List<Object> ob_info = new ArrayList<Object>();
    @JsonProperty("wo_info")
    private List<Object> wo_info = new ArrayList<Object>();
    @JsonProperty("timeframe")
    private List<Object> timeframe = new ArrayList<Object>();

    @JsonIgnore
    private String Name;
    @JsonIgnore
    private String Tag;


    public List<Object> getMaterials() {
        return materials;
    }
    @JsonProperty("materials")
    public void setMaterials(List<Object> materials) {
        this.materials = materials;
    }

    public List<Object> getObject_info() {
        return ob_info;
    }
    @JsonProperty("object_info")
    public void setObject_info(List<Object> object_info) {
        this.ob_info = object_info;
    }

    public List<Object> getWo_info() {
        return wo_info;
    }

    @JsonProperty("wo_info")
    public void setWo_info(List<Object> wo_info) {
        this.wo_info = wo_info;
    }

    public List<Object> getTimeframe() {
        return timeframe;
    }
    @JsonProperty("timeframe")
    public void setTimeframe(List<Object> timeframe) {
        this.timeframe = timeframe;
    }

    public String getStatus() {
        return status;
    }
    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public WoOverview() {

    }

    public String getID() {
        return _id;
    }

    public void setID(String ID) {
        this._id = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static Query getQuestions(Database database) {

        View view = database.getView("app/questions");

        if (view.getMap() == null) {

            view.setMap((document, emitter) -> {
                try {
                    if (document.containsKey("type")) {
                        Log.e("found", " found document " + document.get("type"));
                        emitter.emit(document.get("_id"), null);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }, "1");
        }
        Query query = view.createQuery();
        return query;
    }

    public  static Query getDocumentsByField(Database database, final String field) {

        View view = database.getView("demoapp/");

        if (view.getMap() == null) {

            view.setMap((document, emitter) -> {
                try {
                    if (document.containsKey(field)) {
                        Log.d("CouchBase Database", " found document with type:" + document.get("type") + " and ID " + document.get("_id"));
                        emitter.emit(document.get("_id"), null);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }, "2");
        }
        Query query = view.createQuery();
        return query;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTagg() {
        return Tag;
    }

    public void setTagg(String Tag) {
        this.Tag = Tag;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
