package com.example.coco.demoapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coco.demoapp.Adapters.ObjectAdapter;
import com.example.coco.demoapp.ClickListeners.RecyclerItemClickListener;
import com.example.coco.demoapp.Objects.ObjectsMainList;
import com.example.coco.demoapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;

public class ListScreen extends AppCompatActivity {
    private List<ObjectsMainList> objects = new ArrayList<>();
    private List<ObjectsMainList> active = new ArrayList<>(); //state 1 (the int value inside the ObjectsMainList class)
    private List<ObjectsMainList> finished = new ArrayList<>(); //state 2
    private List<ObjectsMainList> nonpm = new ArrayList<>(); //state 3
    private RecyclerView rView;
    private ObjectAdapter oAdapter;
    private Button btnActive, btnFnished, btnNonPM;
    private int selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments);
        rView = findViewById(R.id.recycle_view);
        selection = 0;
        oAdapter = new ObjectAdapter(objects);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        rView.setAdapter(oAdapter);
        rView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, rView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        ObjectsMainList toSend = new ObjectsMainList();

                        switch (selection) {
                            case 0:
                                toSend = objects.get(position);
                                break;
                            case 1:
                                toSend = active.get(position);
                                break;
                            case 2:
                                toSend = finished.get(position);
                                break;
                            case 3:
                                toSend = nonpm.get(position);
                                break;
                            default:
                                break;
                        }
                        //int number= objects.get(position).getCreation_number();


                        Intent expandable = new Intent(ListScreen.this, ExpandableListActi.class);
                        Toast.makeText(getApplicationContext(), "Pressed " + toSend.getWO().toString(), Toast.LENGTH_LONG).show();

                        expandable.putExtra("Send", toSend);


                        startActivity(expandable);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));
        prepareData();
        populateLists(objects);
        btnActive = findViewById(R.id.btnActive);
        btnFnished = findViewById(R.id.btnFinished);
        btnNonPM = findViewById(R.id.btnActivenonpm);
        btnNonPM.setText("NonPM(" + nonpm.size() + ")");
        btnActive.setText("Active(" + active.size() + ")");
        btnFnished.setText("Finished(" + finished.size() + ")");

        btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = 1;
                oAdapter = new ObjectAdapter(active);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rView.setLayoutManager(mLayoutManager);
                rView.setItemAnimator(new DefaultItemAnimator());
                rView.addItemDecoration(new DividerItemDecoration(rView.getContext(), VERTICAL));
                rView.setAdapter(oAdapter);
            }
        });

        btnFnished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = 2;
                oAdapter = new ObjectAdapter(finished);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rView.setLayoutManager(mLayoutManager);
                rView.setItemAnimator(new DefaultItemAnimator());
                rView.addItemDecoration(new DividerItemDecoration(rView.getContext(), VERTICAL));
                rView.setAdapter(oAdapter);
            }
        });

        btnNonPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = 3;
                oAdapter = new ObjectAdapter(nonpm);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rView.setLayoutManager(mLayoutManager);
                rView.setItemAnimator(new DefaultItemAnimator());
                rView.addItemDecoration(new DividerItemDecoration(rView.getContext(), VERTICAL));
                rView.setAdapter(oAdapter);
            }
        });
    }

    public void populateLists(List<ObjectsMainList> mainList) {
        for (ObjectsMainList o : mainList) {
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

        List<String> elements = new ArrayList<>();
        elements.add("Fan motor failure");
        elements.add("200");
        elements.add("Lorem ipsum");
        elements.add("200kg");
        elements.add("2 NOV 2017");
        elements.add("19 FEB 2017");
        elements.add("Wood");
        elements.add("Iron");

        ObjectsMainList o1 = new ObjectsMainList("Test", "Desc", "6", 1, elements);
        objects.add(o1);
        elements = new ArrayList<>();
        elements.add("Electric motor failure");
        elements.add("420");
        elements.add("Lorem ipsum");
        elements.add("100kg");
        elements.add("12 NOV 2014");
        elements.add("4 FEB 2013");
        elements.add("Diamond");
        elements.add("Iron");

        ObjectsMainList o2 = new ObjectsMainList("Test", "Desc", "625", 1, elements);
        objects.add(o2);

        elements = new ArrayList<>();
        elements.add("Pipe broken");
        elements.add("543");
        elements.add("Lorem ipsum");
        elements.add("456kg");
        elements.add("12 DEC 2014");
        elements.add("4 JAN 2015");
        elements.add("Diamond");
        elements.add("Steel");

        ObjectsMainList o3 = new ObjectsMainList("Test22", "Desc", "63", 2, elements);
        objects.add(o3);

        elements = new ArrayList<>();
        elements.add("Blade damaged");
        elements.add("543");
        elements.add("Lorem ipsum");
        elements.add("1111kg");
        elements.add("12 JUN 2014");
        elements.add("4 OCT 2015");
        elements.add("Diamond");
        elements.add("Wool");

        ObjectsMainList o4 = new ObjectsMainList("Test53", "Desc", "26", 3, elements);
        objects.add(o4);

        ObjectsMainList o5 = new ObjectsMainList("Test26", "Desc", "446", 3, elements);
        objects.add(o5);

        ObjectsMainList o6 = new ObjectsMainList("Test77", "Desc", "56", 3, elements);
        objects.add(o6);

        ObjectsMainList o7 = new ObjectsMainList("Test8567", "Desc", "636", 1, elements);
        objects.add(o7);

        oAdapter.notifyDataSetChanged();
    }


//    public void showActive(View A) {
//        selection = 1;
//        oAdapter = new ObjectAdapter(active);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        rView.setLayoutManager(mLayoutManager);
//        rView.setItemAnimator(new DefaultItemAnimator());
//        rView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
//        rView.setAdapter(oAdapter);
//
//    }
//
//    public void showFinsihed(View A) {
//        selection = 2;
//        oAdapter = new ObjectAdapter(finished);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        rView.setLayoutManager(mLayoutManager);
//        rView.setItemAnimator(new DefaultItemAnimator());
//        rView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
//        rView.setAdapter(oAdapter);
//        //Toast.makeText(getApplicationContext(), "Pressed "+ btnFnished.getText().toString(), Toast.LENGTH_LONG).show();
//    }

//    public void showNopm(View A) {
//        selection = 3;
//        oAdapter = new ObjectAdapter(nonpm);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        rView.setLayoutManager(mLayoutManager);
//        rView.setItemAnimator(new DefaultItemAnimator());
//        rView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
//        rView.setAdapter(oAdapter);
//        //  Toast.makeText(getApplicationContext(), "Pressed "+ btnNonPM.getText().toString(), Toast.LENGTH_LONG).show();
//    }

    public List<ObjectsMainList> listReturn(List<ObjectsMainList> inputList) {

        //TODO: complete code to return the list depeding on the selected view.

        return inputList;
    }
}