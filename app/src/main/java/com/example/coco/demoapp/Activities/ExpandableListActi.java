package com.example.coco.demoapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.example.coco.demoapp.Adapters.ExpandListAdapter;
import com.example.coco.demoapp.Managers.DataManager;
import com.example.coco.demoapp.Model.WoOverview;
import com.example.coco.demoapp.Objects.ExpandListChild;
import com.example.coco.demoapp.Objects.ExpandListGroup;
import com.example.coco.demoapp.Objects.ObjectsMainList;
import com.example.coco.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListActi extends AppCompatActivity {


        /** Called when the activity is first created. */
        private ExpandListAdapter ExpAdapter;
        private ArrayList<ExpandListGroup> ExpListItems;
        private ExpandableListView ExpandList;
        static WoOverview toDosplay;
        private List<WoOverview> mappedDocuments=new ArrayList<>();
        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_expandable_list);
            Intent i = getIntent();


            toDosplay = (WoOverview) i.getExtras().getSerializable("Send");

            ExpandList = findViewById(R.id.ExpList);
            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(ExpandableListActi.this, ExpListItems);
            ExpandList.setAdapter(ExpAdapter);


            DataManager manager = DataManager.getSharedInstance(getApplicationContext());


            mappedDocuments = manager.mapDocument("type");

            for(WoOverview wo : mappedDocuments){

                Log.d("CSID", wo.getID() + " " + wo.getType() + " " +wo.getStatus()+" " + wo.get_rev());
            }
        }


        public ArrayList<ExpandListGroup> SetStandardGroups () {

            ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
            ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();


            ExpandListGroup gru1 = new ExpandListGroup();

            gru1.setName("WO Info");
            ExpandListChild ch1_1 = new ExpandListChild();
            ch1_1.setName("Damage info: ");
            ch1_1.setTagg(toDosplay.getWo_info().get(0).toString());
            list2.add(ch1_1);
            ExpandListChild ch1_2 = new ExpandListChild();
            ch1_2.setName("Work Type: ");
            ch1_2.setTagg(toDosplay.getWo_info().get(1).toString());
            list2.add(ch1_2);
            ExpandListChild ch1_3 = new ExpandListChild();
            ch1_3.setName("Fault description: ");
            ch1_3.setTagg(toDosplay.getWo_info().get(2).toString());
            list2.add(ch1_3);
            gru1.setItems(list2);
            list2 = new ArrayList<>();


            ExpandListGroup gru2 = new ExpandListGroup();
            gru2.setName("Object Info");
            ExpandListChild ch2_1 = new ExpandListChild();
            ch2_1.setName("Make:  ");
            ch2_1.setTagg(toDosplay.getObject_info().get(0).toString());
            list2.add(ch2_1);
            ExpandListChild ch2_2 = new ExpandListChild();
            ch2_2.setName("Model  ");
            ch2_2.setTagg(toDosplay.getObject_info().get(1).toString());
            list2.add(ch2_2);
            ExpandListChild ch2_3 = new ExpandListChild();
            ch2_3.setName("VIN Number");
            ch2_3.setTagg(toDosplay.getObject_info().get(2).toString());
            list2.add(ch2_3);
            gru2.setItems(list2);
            list2 = new ArrayList<>();

            ExpandListGroup gru3 = new ExpandListGroup();
            gru3.setName("Timeline");
            ExpandListChild ch3_1 = new ExpandListChild();
            ch3_1.setName("Work starting: ");
            ch3_1.setTagg(toDosplay.getTimeframe().get(0).toString());
            list2.add(ch3_1);
            ExpandListChild ch3_2 = new ExpandListChild();
            ch3_2.setName("Finish date (estimate)");
            ch3_2.setTagg(toDosplay.getTimeframe().get(1).toString());
            list2.add(ch3_2);
            gru3.setItems(list2);
            list2 = new ArrayList<>();


            ExpandListGroup gru4 = new ExpandListGroup();
            gru4.setName("Materials");
            int object_count =0;
        for( Object s : toDosplay.getMaterials()){

           ExpandListChild ch = new ExpandListChild();
           ch.setName("Material " + (object_count+1));
           ch.setTagg(toDosplay.getMaterials().get(object_count).toString());
           object_count++;
           list2.add(ch);
        }
            gru4.setItems(list2);


            list.add(gru1);
            list.add(gru2);
            list.add(gru3);
            list.add(gru4);


            return list;
        }
    }

