package com.example.coco.demoapp.Presenters;

import android.app.Activity;
import android.util.Log;

import com.example.coco.demoapp.Managers.DataManager;
import com.example.coco.demoapp.Model.WoOverview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandrumihai1 on 10/01/2018.
 */

public class CarPresenter {
    private Activity activity;
    private DataManager manager;




    public CarPresenter(Activity activity) {
        this.activity = activity;
        manager = DataManager.getSharedInstance(activity);
    }

    public void submitNewCar(Map<String, Object> carToAdd) {
        manager.createDocument(carToAdd);
        Log.d("CSID", "New doc. added successfully.");
    }


}
