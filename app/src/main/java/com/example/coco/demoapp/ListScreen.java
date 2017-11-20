package com.example.coco.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListScreen extends AppCompatActivity {
    private List<ObjectsMainList> objects = new ArrayList<>();
    private List<ObjectsMainList> active = new ArrayList<>(); //state 1 (the int value inside the ObjectsMainList class)
    private List<ObjectsMainList> finished = new ArrayList<>(); //state 2
    private List<ObjectsMainList> nonpm = new ArrayList<>(); //state 3
    private RecyclerView rView;
    private ObjectAdapter oAdapter;
    private Button btnActive, btnFnished, btnNonPM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments);
        rView = findViewById(R.id.recycle_view);
        oAdapter = new ObjectAdapter(objects);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rView.setAdapter(oAdapter);
        rView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, rView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        ObjectsMainList toSend= objects.get(position);



                        Intent expandable = new Intent(ListScreen.this,ExpandableListActi.class);
                        Toast.makeText(getApplicationContext(), "Pressed "+ toSend.getWO().toString(), Toast.LENGTH_LONG).show();

                        expandable.putExtra("Send",toSend);


                        startActivity(expandable);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                }));
        prepareData();
        populateLists(objects);
        btnActive=findViewById(R.id.btnActive);
        btnFnished=findViewById(R.id.btnFinished);
        btnNonPM=findViewById(R.id.btnActivenonpm);
        btnNonPM.setText("NonPM("+nonpm.size()+")");
        btnActive.setText("Active("+active.size()+")");
        btnFnished.setText("Finished("+finished.size()+")");
    }

    public void populateLists(List<ObjectsMainList> mainList) {
        for(ObjectsMainList o : mainList){
            switch (o.getState()) {
                case 1:
                    active.add(o);
                    break;
                case 2:
                    finished.add(o);
                    break;
                case 3:
                    nonpm.add(o);
                    break;
            }
        }

    }

    private void prepareData() {

      /*  Map<String,List<String>> myMap = new HashMap<>();

        List<String> woInfo = new ArrayList<>();
        List<String> objectInfo=new ArrayList<>();
        List<String> timeframe = new ArrayList<>();
        List<String> materials = new ArrayList<>();

        woInfo.add("Fan motor failure");
        woInfo.add("200");
        woInfo.add("Lorem ipsum");
        myMap.put("WO Info",woInfo);

        objectInfo.add("200kg");
        objectInfo.add("20/10/1999");
        myMap.put("Object Info",objectInfo);

        timeframe.add("19 FEB 2017");
        timeframe.add("2 NOV 2017");
        myMap.put("Timeframe",timeframe);

        materials.add("Wood");
        materials.add("Iron");
        myMap.put("Materials",materials);





        ObjectSubelement o =new ObjectSubelement("Information",myMap);*/
      List<String> elements = new ArrayList<>();
        elements.add("Fan motor failure");
        elements.add("200");
        elements.add("Lorem ipsum");
        elements.add("200kg");
        elements.add("2 NOV 2017");
        elements.add("19 FEB 2017");
        elements.add("Wood");
        elements.add("Iron");

        ObjectsMainList o1 = new ObjectsMainList("Test", "Desc", "6", 1,elements);
        objects.add(o1);

        ObjectsMainList o2 = new ObjectsMainList("Test", "Desc", "625", 1,elements);
        objects.add(o2);

        ObjectsMainList o3 = new ObjectsMainList("Test22", "Desc", "63", 2,elements);
        objects.add(o3);

        ObjectsMainList o4 = new ObjectsMainList("Test53", "Desc", "26", 3,elements);
        objects.add(o4);

        ObjectsMainList o5 = new ObjectsMainList("Test26", "Desc", "446", 3,elements);
        objects.add(o5);

        ObjectsMainList o6 = new ObjectsMainList("Test77", "Desc", "56", 3,elements);
        objects.add(o6);

        ObjectsMainList o7 = new ObjectsMainList("Test8567", "Desc", "636", 1,elements);
        objects.add(o7);

        oAdapter.notifyDataSetChanged();
    }


    public void showActive(View A) {
        oAdapter = new ObjectAdapter(active);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rView.setAdapter(oAdapter);
    }

    public void showFinsihed(View A) {
        oAdapter = new ObjectAdapter(finished);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rView.setAdapter(oAdapter);
    }

    public void showNopm(View A) {
        oAdapter = new ObjectAdapter(nonpm);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rView.setAdapter(oAdapter);
    }
}