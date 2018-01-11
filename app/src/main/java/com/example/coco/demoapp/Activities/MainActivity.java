package com.example.coco.demoapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.example.coco.demoapp.Managers.DataManager;
import com.example.coco.demoapp.Managers.ModelHelper;
import com.example.coco.demoapp.Model.WoOverview;
import com.example.coco.demoapp.Objects.Users;
import com.example.coco.demoapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.coco.demoapp.Managers.DataManager.database;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView txtUsername, txtPassword, txtAlert;
    private CheckBox cboxPeek, cboxRemember;
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefedit;
    private Boolean users_already_stored = false, remember_me = false;
    private Set<Users> user_list = new HashSet<>();



    //no duplicates
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //button initializations
        cboxPeek = findViewById(R.id.cboxPick);
        cboxRemember = findViewById(R.id.cboxRemember);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUserName);
        btnLogin = findViewById(R.id.btnLogin);
        txtAlert = findViewById(R.id.txtAlert);
        txtAlert.setVisibility(View.INVISIBLE);

        //create some users and store them in shared prefs.
        //a boolean value is store in the chace memory and it will tell us if the users
        //are already stored or not and act accordingly, this will spare us the trouble of storing the users
        //inside  sharedpreferences everytime when the app is launched
        prefs = getSharedPreferences("Users", MODE_PRIVATE);
        prefedit = prefs.edit();
        users_already_stored = prefs.getBoolean("stored", false);
        remember_me = prefs.getBoolean("remember", false);
        prefedit.commit();
        checkRemember(remember_me);
        store_users(users_already_stored);
        //next, the users are created using the data stored inside the sharedprefrences
        createUsers();

        DataManager manager = DataManager.getSharedInstance(getApplicationContext());

        manager.getDocumentsByField("type");
        Log.d("CouchBase Database", manager.retrieveDocument("234243324").toString());

//         List<WoOverview> mappedDocuments=new ArrayList<>();
//        mappedDocuments = manager.mapDocument("type");
//
//        for(WoOverview wo : mappedDocuments){
//
//            Log.d("CSID", wo.getID() + " " + wo.getType() + " " +wo.getStatus()+" " + wo.get_rev());
//        }

    }

    public void peekPassword(View cbox) {
        if (cboxPeek.isChecked()) {
            txtPassword.setInputType(InputType.TYPE_CLASS_TEXT);

        }
        if (!cboxPeek.isChecked()) {
            txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            //removed after testing:  txtPassword.setText("am debifat check");
        }

    }

    public void login(View loginbtn) {
        if (checkFields()) {
            boolean match = false;
            String input_user = txtUsername.getText().toString();
            String input_password = txtPassword.getText().toString();

            for (Users u : user_list) {
                if (u.getUsername().compareTo(input_user) == 0 && u.getPassword().compareTo(input_password) == 0) {
                    Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                    match = true;
                    //removed after testing:     txtAlert.setText("Welcome aboard, " + input_user + "!");
                    //removed after testing:     txtAlert.setVisibility(View.VISIBLE);
                    Intent listscreen = new Intent(MainActivity.this, ListScreen.class);
                    listscreen.putExtra("acc_logged", u.getUsername().toString());
                    startActivity(listscreen);
                    break;
                }
            }
            if (!match) {
                txtAlert.setText(R.string.invalid_credentials);
                txtAlert.setVisibility(View.VISIBLE);
            }
        }
    }

    public boolean checkFields() {
        if (txtUsername.getText().toString().length() == 0 || txtPassword.getText().toString().length() == 0) {
            txtAlert.setText(R.string.empty_fields);
            txtAlert.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    public void store_users(boolean stored) {
        if (!stored) {
            prefs = getSharedPreferences("Users", MODE_PRIVATE);
            prefedit = prefs.edit();
            prefedit.putString("Usr1", "acc_gigel");
            prefedit.putString("Pw1", "parolagigel1");
            //
            prefedit.putString("Usr2", "acc_alt_gigel");
            prefedit.putString("Pw2", "parolagigel2");
            //
            prefedit.putString("Usr3", "acc_not_gigel");
            prefedit.putString("Pw3", "parolagigel3");
            prefedit.commit();
            users_already_stored = true;
            prefedit.putBoolean("stored", true);
            prefedit.commit();
            //removed after testing:  Toast.makeText(getApplicationContext(), "Users stored!", Toast.LENGTH_LONG).show();
        }
      /*removed after testing:  else   {
            //removed after testing:   Toast.makeText(getApplicationContext(), "Users were stored on a previous use!", Toast.LENGTH_LONG).show();
        }*/
    }

    public void createUsers() {
        Users u1 = new Users(prefs.getString("Usr1", "Default"), prefs.getString("Pw1", "Default"));
        Users u2 = new Users(prefs.getString("Usr2", "Default"), prefs.getString("Pw2", "Default"));
        Users u3 = new Users(prefs.getString("Usr3", "Default"), prefs.getString("Pw3", "Default"));
        user_list.add(u1);
        user_list.add(u2);
        user_list.add(u3);
    }

    public void rememberClick(View cboxRmmbr) {
        //what happens when the remember me checkbox is checked/unchecked
        if (cboxRemember.isChecked()) {
            prefedit.putBoolean("remember", true);
            prefedit.putString("remembered_id", txtUsername.getText().toString());
            prefedit.putString("remembered_pwd", txtPassword.getText().toString());
            prefedit.commit();
        } else {
            prefedit.putBoolean("remember", false);
            prefedit.commit();
        }

    }

    public void checkRemember(boolean rmmbr) {
        //check if the remember checkbox is checked, abd ct accordingly
        if (rmmbr) {
            txtPassword.setText(prefs.getString("remembered_pwd", ""));
            txtUsername.setText(prefs.getString("remembered_id", ""));
            cboxRemember.setChecked(true);
        } else {
            txtPassword.setText("");
            txtUsername.setText("");
            cboxRemember.setChecked(false);
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
