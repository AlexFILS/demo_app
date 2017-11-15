package com.example.coco.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListScreen extends AppCompatActivity {
private List<ObjectsMainList> objects = new ArrayList<>();
private RecyclerView rView;
private ObjectAdapter oAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments);
        rView = findViewById(R.id.recycle_view);
        oAdapter= new ObjectAdapter(objects);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rView.setAdapter(oAdapter);

        prepareData();
    }

    private void prepareData(){
    ObjectsMainList o1 = new ObjectsMainList("Test","Desc","6");
    objects.add(o1);

        ObjectsMainList o2 = new ObjectsMainList("Test","Desc","625");
        objects.add(o2);

        ObjectsMainList o3 = new ObjectsMainList("Test22","Desc","63");
        objects.add(o3);

        ObjectsMainList o4 = new ObjectsMainList("Test53","Desc","26");
        objects.add(o4);

        ObjectsMainList o5 = new ObjectsMainList("Test26","Desc","446");
        objects.add(o5);

        ObjectsMainList o6 = new ObjectsMainList("Test77","Desc","56");
        objects.add(o6);

        ObjectsMainList o7 = new ObjectsMainList("Test8567","Desc","636");
        objects.add(o7);

        oAdapter.notifyDataSetChanged();
    }
}
