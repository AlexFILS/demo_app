package com.example.coco.demoapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;
import static java.util.Comparator.comparingInt;

public class ListScreen extends AppCompatActivity {
    private List<WoOverview> objects = new ArrayList<>();
    private List<WoOverview> active = new ArrayList<>(); //state 1 (the int value inside the ObjectsMainList class)
    private List<WoOverview> finished = new ArrayList<>(); //state 2
    private List<WoOverview> nonpm = new ArrayList<>(); //state 3
    private List<WoOverview> mappedDocuments = new ArrayList<>();
    private List<WoOverview> searchedCar = new ArrayList<>();
    private RecyclerView rView;
    private ObjectAdapter oAdapter;
    private Button btnActive, btnFnished, btnNonPM, newCar, search;
    private TextView txtSearch;
    private int selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragments);
        rView = findViewById(R.id.recycle_view);
        selection = 0;
        objects = DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("type", "wo");
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
                            case 4:
                                toSend = searchedCar.get(position);
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
        populateLists(DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("type", "wo"));
        oAdapter.notifyDataSetChanged();
        btnActive = findViewById(R.id.btnActive);
        btnFnished = findViewById(R.id.btnFinished);
        search = findViewById(R.id.btnSearch);
        txtSearch = findViewById(R.id.txtRegSrc);
        btnNonPM = findViewById(R.id.btnActivenonpm);
        btnNonPM.setText("NonPM(" + nonpm.size() + ")");
        btnActive.setText("Active(" + active.size() + ")");
        btnFnished.setText("Finished(" + finished.size() + ")");
        newCar = findViewById(R.id.btnAddCar);

        btnActive.setOnClickListener(view -> {
            selection = 1;
            oAdapter = new ObjectAdapter(active);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
            rView.setLayoutManager(mLayoutManager1);
            rView.setItemAnimator(new DefaultItemAnimator());
            rView.addItemDecoration(new DividerItemDecoration(rView.getContext(), VERTICAL));
            rView.setAdapter(oAdapter);
        });

        btnFnished.setOnClickListener(view -> {
            selection = 2;
            oAdapter = new ObjectAdapter(finished);
            RecyclerView.LayoutManager mLayoutManager12 = new LinearLayoutManager(getApplicationContext());
            rView.setLayoutManager(mLayoutManager12);
            rView.setItemAnimator(new DefaultItemAnimator());
            rView.addItemDecoration(new DividerItemDecoration(rView.getContext(), VERTICAL));
            rView.setAdapter(oAdapter);
        });

        btnNonPM.setOnClickListener(view -> {
            selection = 3;
            oAdapter = new ObjectAdapter(nonpm);
            RecyclerView.LayoutManager mLayoutManager13 = new LinearLayoutManager(getApplicationContext());
            rView.setLayoutManager(mLayoutManager13);
            rView.setItemAnimator(new DefaultItemAnimator());
            rView.addItemDecoration(new DividerItemDecoration(rView.getContext(), VERTICAL));
            rView.setAdapter(oAdapter);
        });

        newCar.setOnClickListener(view -> {
            Intent newCarWindows = new Intent(getApplicationContext(), AddCar.class);
            startActivity(newCarWindows);

        });
        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: {
                        objects.sort((WoOverview o1, WoOverview o2) -> o1.get_id().compareTo(o2.get_id()));
                        oAdapter = new ObjectAdapter(objects);
                        rView.setAdapter(oAdapter);
                        break;
                    }
                    case 1: {
                        objects.sort((WoOverview o1, WoOverview o2) -> o1.getDescription().compareTo(o2.getDescription()));
                        oAdapter = new ObjectAdapter(objects);
                        rView.setAdapter(oAdapter);
                        break;
                    }
                    case 2: {
                        objects.sort((WoOverview o1, WoOverview o2) -> o1.getStatus().compareTo(o2.getStatus()));
                        oAdapter = new ObjectAdapter(objects);
                        rView.setAdapter(oAdapter);
                        break;
                    }
                    case 3: {
                        objects.sort((o1, o2) -> Integer.signum(Integer.parseInt(o1.getPrice()) - Integer.parseInt(o2.getPrice())));
                        oAdapter = new ObjectAdapter(objects);
                        rView.setAdapter(oAdapter);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void populateLists(List<WoOverview> mainList) {
        for (WoOverview o : mainList) {
            switch (o.getStatus()) {
                case "repaired":
                    active.add(o);
                    break;
                case "in progress":
                    finished.add(o);
                    break;
                case "not started":
                    nonpm.add(o);
                    break;
            }
        }
        search = findViewById(R.id.btnSearch);
        search.setOnClickListener((View view) -> {
            searchedCar.clear();
            boolean found = false;
            selection = 4;
            if (txtSearch.getText().toString().compareTo("") == 0) {
                oAdapter = new ObjectAdapter(objects);
                rView.setAdapter(oAdapter);
                selection = 0;
            } else {

                Log.d("CSID", DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("description", txtSearch.getText().toString()) + " " + txtSearch.getText().toString());
                //  searchedCar = DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("type", "wo");

//                for (WoOverview wo : DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("type", "wo")) {
//                    if (wo.getDescription().compareTo(txtSearch.getText().toString()) == 0) {
//                        searchedCar.add(wo);
//                        oAdapter = new ObjectAdapter(searchedCar);
//                        rView.setAdapter(oAdapter);
//                        found=true;
//                    }
                // }
                DataManager.getSharedInstance(getApplicationContext()).mapDocOfSpecificType("description", "wo")
                        .stream()
                        .filter(wo -> wo.getDescription().compareTo(txtSearch.getText().toString()) == 0)
                        .forEach(wo -> searchedCar.add(wo));

                oAdapter = new ObjectAdapter(searchedCar);
                rView.setAdapter(oAdapter);

                found = searchedCar.size() > 0;

            }
            if (found) {
                Toast.makeText(getApplicationContext(), "Car found!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Car not found/list cleared.", Toast.LENGTH_LONG).show();
            }
            txtSearch.setText("");
        });

    }

    private void prepareData() {
        mappedDocuments = DataManager.getSharedInstance(getApplicationContext()).mapDocument("type");

        oAdapter.notifyDataSetChanged();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }
}