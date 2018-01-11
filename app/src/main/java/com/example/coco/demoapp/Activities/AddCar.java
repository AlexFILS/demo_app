package com.example.coco.demoapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coco.demoapp.Presenters.CarPresenter;
import com.example.coco.demoapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class AddCar extends AppCompatActivity implements Serializable {
    private TextView dmgInfo, workInfo, faultDesc, make, model, vin, start, end, materials, plate;
    private Map<String, Object> newDocument = new HashMap<>();
    private List<TextView> Views = new ArrayList<>();
    CarPresenter presenter;


    private List<Object> materialsL = new ArrayList<Object>();

    private List<Object> ob_info = new ArrayList<Object>();

    private List<Object> wo_info = new ArrayList<Object>();

    private List<Object> timeframe = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        dmgInfo = findViewById(R.id.txtDmgInfo);
        workInfo = findViewById(R.id.txtWorkType);
        faultDesc = findViewById(R.id.txtFaultDesc);
        make = findViewById(R.id.txtMake);
        model = findViewById(R.id.txtModel);
        vin = findViewById(R.id.txtVIN);
        start = findViewById(R.id.txtStart);
        end = findViewById(R.id.txtEnd);
        materials = findViewById(R.id.txtMaterials);
        plate = findViewById(R.id.txtPlate);
        Button submit = findViewById(R.id.btnSubmit);
        presenter = new CarPresenter(this);

        Views.add(dmgInfo);
        Views.add(workInfo);
        Views.add(faultDesc);
        Views.add(make);
        Views.add(model);
        Views.add(vin);
        Views.add(start);
        Views.add(end);
        Views.add(materials);
        Views.add(plate);


        submit.setOnClickListener((View view) -> {
            int is_ok = 0;
            for (TextView tw : Views) {
                if (Objects.equals(tw.getText().toString(), "")) {
                    tw.setError("Cannot be empty");
                } else
                    is_ok += 1;

            }

            if(is_ok==Views.size()) {
                ob_info.add(make.getText().toString());
                ob_info.add(model.getText().toString());
                ob_info.add(vin.getText().toString());

                timeframe.add(start.getText().toString());
                timeframe.add(end.getText().toString());

                wo_info.add(dmgInfo.getText().toString());
                wo_info.add(workInfo.getText().toString());
                wo_info.add(faultDesc.getText().toString());

                materialsL.add(materials.getText().toString());

                newDocument.put("_id", "3536dd33TET1");

                newDocument.put("description", plate.getText().toString());
                newDocument.put("object_info", ob_info);

                newDocument.put("status", "not started");
                newDocument.put("price", "435");
                newDocument.put("type", "wo");
                newDocument.put("wo_info", wo_info);
                newDocument.put("timeframe", timeframe);

                newDocument.put("materials", materialsL);
                presenter.submitNewCar(newDocument);
                this.finish();
            }
            else
                Toast.makeText(getApplicationContext(),"There are empty fields!",Toast.LENGTH_LONG).show();
        });

    }
}
