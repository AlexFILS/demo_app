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
import com.example.coco.demoapp.Managers.DataManager;
import com.example.coco.demoapp.Model.WoOverview;
import com.example.coco.demoapp.Objects.ExpandListChild;
import com.example.coco.demoapp.Objects.ExpandListGroup;
import com.example.coco.demoapp.Objects.ObjectsMainList;
import com.example.coco.demoapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;

public class ListScreen extends AppCompatActivity {
    private List<WoOverview> objects = new ArrayList<>();
    private List<WoOverview> active = new ArrayList<>(); //state 1 (the int value inside the ObjectsMainList class)
    private List<WoOverview> finished = new ArrayList<>(); //state 2
    private List<WoOverview> nonpm = new ArrayList<>(); //state 3
    private List<WoOverview> mappedDocuments = new ArrayList<>();
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
        objects=DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("type","wo");
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

                        WoOverview toSend = new WoOverview();

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
                        Toast.makeText(getApplicationContext(), "Pressed " + toSend.getID().toString(), Toast.LENGTH_LONG).show();

                        expandable.putExtra("Send", toSend);


                        startActivity(expandable);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));
        prepareData();
        populateLists(DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("type","wo"));
        oAdapter.notifyDataSetChanged();
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

    public void populateLists(List<WoOverview> mainList) {
        for (WoOverview o : mainList) {
            switch (o.getStatus()) {
                case "active":
                    active.add(o);
                    break;
                case "inactive":
                    finished.add(o);
                    break;
                case "nopm":
                    nonpm.add(o);
                    break;
            }
        }

    }

    private void prepareData() {
        mappedDocuments = DataManager.getSharedInstance(getApplicationContext()).mapDocument("type");

        oAdapter.notifyDataSetChanged();
    }

    public List<ObjectsMainList> listReturn(List<ObjectsMainList> inputList) {

        //TODO: complete code to return the list depeding on the selected view.

        return inputList;
    }
}