package com.example.coco.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListScreen extends AppCompatActivity {
private String loggedin;
private TextView loggedUsr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        Intent getLogged=getIntent();
        loggedin=getLogged.getStringExtra("acc_logged");
        loggedUsr=findViewById(R.id.txtLogged);
        loggedUsr.setText("Logged in as: " + loggedin);
    }
}
