package com.example.creditmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button viewAllUsers=null;
    ImageView logo=null;
    TextView displayUsersTV=null;
    RelativeLayout relativeLayout=null;
    ListView listView=null;
    SQLiteDatabase usersTable=null;
    ArrayList<String> usersArray=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        viewAllUsers = (Button) findViewById(R.id.ViewUsersbtn);
        logo = (ImageView) findViewById(R.id.imageView);
        displayUsersTV = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);

        listView.setVisibility(View.INVISIBLE);
        displayUsersTV.setVisibility(View.INVISIBLE);
        usersArray = new ArrayList<String>();

        usersTable = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);


        usersTable.execSQL("CREATE TABLE IF NOT EXISTS userDetails(name VARCHAR,email VARCHAR,credit INTEGER(5))");
        usersTable.execSQL("CREATE TABLE IF NOT EXISTS transferTable(name VARCHAR,credit INTEGER(5))");
        usersTable.execSQL("INSERT INTO userDetails (name, email, credit) VALUES ('harshit','harshit@gmail.com','60')");
        usersTable.execSQL("INSERT INTO userDetails (name, email, credit) VALUES ('tom','tom@gmail.com','10')");
        usersTable.execSQL("INSERT INTO userDetails (name, email, credit) VALUES ('rahul','rahul@gmail.com','30')");

        usersArray.add("Harshit");
        usersArray.add("Tom");
        usersArray.add("Rahul");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usersArray);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.INVISIBLE);
                logo.setVisibility(View.VISIBLE);
                relativeLayout.setBackgroundColor(Color.parseColor("#2e3237"));
                viewAllUsers.setVisibility(View.VISIBLE);
                displayUsersTV.setVisibility(View.INVISIBLE);
            }
        });

        viewAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllUsers.setVisibility(View.INVISIBLE);
                logo.setVisibility(View.INVISIBLE);
                displayUsersTV.setVisibility(View.VISIBLE);
                relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                listView.setVisibility(View.VISIBLE);

            }
        });
    }
}
