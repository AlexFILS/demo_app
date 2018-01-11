package com.example.coco.demoapp.Managers;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseOptions;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Manager;

import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryOptions;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.auth.AuthenticatorFactory;
import com.couchbase.lite.javascript.JavaScriptReplicationFilterCompiler;
import com.couchbase.lite.javascript.JavaScriptViewCompiler;
import com.couchbase.lite.listener.Credentials;
import com.couchbase.lite.listener.LiteListener;
import com.couchbase.lite.replicator.Replication;
import com.example.coco.demoapp.Model.WoOverview;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by alexandrumihai1 on 04/01/2018.
 */

public class DataManager extends AppCompatActivity implements Replication.ChangeListener{


    public static Database database;
    private static DataManager instance = null;
    final static String TAG = "CouchBase Database";
    private static Manager manager = null;

    protected DataManager(Context context) {


        //couchbase stuff begins here
        Log.d(TAG, "Begin Couchbase Database");


        try {
            manager = new Manager(new AndroidContext(context), Manager.DEFAULT_OPTIONS);
            DatabaseOptions options = new DatabaseOptions();
            options.setCreate(true);
            database =  manager.openDatabase("demoapp",options);
            Log.d(TAG, "Manager created");

            View.setCompiler(new JavaScriptViewCompiler());
            Database.setFilterCompiler(new JavaScriptReplicationFilterCompiler());

            Credentials credentials = new Credentials(null, null);
            LiteListener liteListener = new LiteListener(manager, 5984, credentials);

            Thread thread = new Thread(liteListener);
            thread.start();

            startSync();

        } catch (IOException e) {
            Log.e(TAG, "Cannot create manager object");
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }


    }

    public  void createDocument(Map<String, Object> docContent) {
        Document document = database.createDocument();
        try {
            document.putProperties(docContent);
            Log.d(TAG, "Document written to database  with ID = " + document.getId());
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Cannot write document to database", e);
        }


    }

    public  Document retrieveDocument(String docID) {
        Document retrievedDocument = database.getDocument(docID);

        Log.d(TAG, "retrievedDocument=" + String.valueOf(retrievedDocument.getId()));
        Log.d(TAG, "retrievedDocument prop=" + String.valueOf(retrievedDocument.getProperties()));
        return retrievedDocument;
    }

    public static DataManager getSharedInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    public  void updateDoc(Map<String, Object> updatedProperties, String docID) {
        try {
            Document retrievedDocument = retrieveDocument(docID);
            retrievedDocument.putProperties(updatedProperties);
            Log.d(TAG, "updated retrievedDocument=" + String.valueOf(retrievedDocument.getProperties()));
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Cannot update document", e);
        }
    }
    public void startSync() {

        URL syncUrl;
        try {
            syncUrl = new URL("http://localhost:4984/demoapp/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        Replication pullReplication = database.createPullReplication(syncUrl);
        pullReplication.setContinuous(true);

        Replication pushReplication = database.createPushReplication(syncUrl);
        pushReplication.setContinuous(true);

        pullReplication.start();
        pushReplication.start();
       pullReplication.addChangeListener(this);
        pushReplication.addChangeListener(this);
       Log.d("CouchBase Database","++++++"+database.getName()+ database.getDocumentCount());
    }

    @Override
    public void changed(Replication.ChangeEvent event) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void getDocumentsByField(final String field){
        QueryEnumerator questions = null;
        try {
            questions = WoOverview.getDocumentsByField(database,field).run();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }


        List<WoOverview> data = new ArrayList<>();
        for (QueryRow question : questions) {
            Document document = question.getDocument();
            WoOverview model = ModelHelper.modelForDocument(document, WoOverview.class);
            data.add(model);
            Log.d("CouchBase Database--",   model.getID());
            Log.d("CouchBase Database++",   data.get(0).getType());
        }

    }

    public  List<WoOverview> mapDocument(String docType){
        QueryEnumerator workOrders=null;
        List<WoOverview> data = new ArrayList<>();
        try {
            workOrders = WoOverview.getDocumentsByField(database,docType).run();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        for (QueryRow workOrder : workOrders) {

            Document document = workOrder.getDocument();
            WoOverview model = ModelHelper.modelForDocument(document, WoOverview.class);
            data.add(model);

        }
           return data;
    }

    public List<WoOverview> mapDocOfSpecificType(String property, String propertyValue){
        QueryEnumerator workOrders=null;
        List<WoOverview> data = new ArrayList<>();
        try {
            workOrders = WoOverview.getDocumentsByField(database,property).run();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        for (QueryRow workOrder : workOrders) {

            Document document = workOrder.getDocument();
            WoOverview model = ModelHelper.modelForDocument(document, WoOverview.class);
            if(model.getType().compareTo(propertyValue)==0) {
                data.add(model);
            }
        }
        return data;

    }
}

