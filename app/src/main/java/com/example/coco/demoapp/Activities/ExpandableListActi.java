package com.example.coco.demoapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        static ObjectsMainList toDosplay;
        private List<WoOverview> mappedDocuments=new ArrayList<>();
        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_expandable_list);
            Intent i = getIntent();


            toDosplay = (ObjectsMainList) i.getExtras().getSerializable("Send");

            ExpandList = findViewById(R.id.ExpList);
            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(ExpandableListActi.this, ExpListItems);
            ExpandList.setAdapter(ExpAdapter);


            DataManager manager = DataManager.getSharedInstance(getApplicationContext());

            //  manager.getDocumentsByField("type");
            // Log.d("CouchBase Database",    manager.retrieveDocument("234243324").toString());

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
            ch1_1.setName("Directive: ");
            ch1_1.setTagg(toDosplay.returnSpecificItem(0));
            list2.add(ch1_1);
            ExpandListChild ch1_2 = new ExpandListChild();
            ch1_2.setName("Work Type: ");
            ch1_2.setTagg(toDosplay.returnSpecificItem(1));
            list2.add(ch1_2);
            ExpandListChild ch1_3 = new ExpandListChild();
            ch1_3.setName("Fault description: ");
            ch1_3.setTagg(toDosplay.returnSpecificItem(2));
            list2.add(ch1_3);
            gru1.setItems(list2);
            list2 = new ArrayList<>();


            ExpandListGroup gru2 = new ExpandListGroup();
            gru2.setName("Object Info");
            ExpandListChild ch2_1 = new ExpandListChild();
            ch2_1.setName("Start date:  ");
            ch2_1.setTagg(toDosplay.returnSpecificItem(4));
            list2.add(ch2_1);
            ExpandListChild ch2_2 = new ExpandListChild();
            ch2_2.setName("Start date:  ");
            ch2_2.setTagg(toDosplay.returnSpecificItem(5));
            list2.add(ch2_2);
            gru2.setItems(list2);
            list2 = new ArrayList<>();

            ExpandListGroup gru3 = new ExpandListGroup();
            gru3.setName("Timeline");
            ExpandListChild ch3_1 = new ExpandListChild();
            ch3_1.setName("Somenradomdetail: ");
            ch3_1.setTagg(toDosplay.returnSpecificItem(3));
            list2.add(ch3_1);
            gru3.setItems(list2);
            list2 = new ArrayList<>();


            ExpandListGroup gru4 = new ExpandListGroup();
            gru4.setName("Materials");
            ExpandListChild ch4_1 = new ExpandListChild();
            ch4_1.setName("Material 1:  ");
            ch4_1.setTagg(toDosplay.returnSpecificItem(6));
            list2.add(ch4_1);
            ExpandListChild ch4_2 = new ExpandListChild();
            ch4_2.setName("Material 2:  ");
            ch4_2.setTagg(toDosplay.returnSpecificItem(7));
            list2.add(ch4_2);
            gru4.setItems(list2);

            list.add(gru1);
            list.add(gru2);
            list.add(gru3);
            list.add(gru4);


            return list;
        }
    }

