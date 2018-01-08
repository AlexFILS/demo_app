package com.example.coco.demoapp.Model;

import android.util.Log;

import com.couchbase.lite.Database;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.View;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * Created by alexandrumihai1 on 04/01/2018.
 */

public class WoOverview {
    private String _id;
    private String type;
    private String _rev;
    private String description;
    private String status;



    @JsonIgnore
    private String Name;
    @JsonIgnore
    private String Tag;

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

            view.setMap(new Mapper() {
                @Override

                public void map(Map<String, Object> document, Emitter emitter) {
                    try {
                        if (document.containsKey("type")) {
                            Log.e("found", " found document " + document.get("type"));
                            emitter.emit(document.get("_id"), null);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                }
            }, "1");
        }
        Query query = view.createQuery();
        return query;
    }

    public  static Query getDocumentsByField(Database database, final String field) {

        View view = database.getView("demoapp/");

        if (view.getMap() == null) {

            view.setMap(new Mapper() {
                @Override

                public void map(Map<String, Object> document, Emitter emitter) {
                    try {
                        if (document.containsKey(field)) {
                            Log.d("CouchBase Database", " found document with type:" + document.get("type") + " and ID " + document.get("_id"));
                            emitter.emit(document.get("_id"), null);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

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
}
