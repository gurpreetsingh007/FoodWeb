package com.example.final_foodweb_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class viewNotifications extends AppCompatActivity {

    ListView notificationsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);

        notificationsListView = findViewById(R.id.notificationsList);

        final ArrayList<String> notificationsArray = new ArrayList<>();

        notificationsArray.add("\n"+"Student Org 1"+"\n"+"Pickup Time: 1 PM"+"\n");
        notificationsArray.add("\n"+"Food Collective"+"\n"+"Pickup Time: 2 PM"+"\n");
        notificationsArray.add("\n"+"Student Org 2"+"\n"+"Pickup Time: 3 PM"+"\n");
        notificationsArray.add("\n"+"Cheese Louise"+"\n"+"Pickup Time: 4 PM"+"\n");
        notificationsArray.add("\n"+"Take a Pizza Me"+"\n"+"Pickup Time: 5 PM"+"\n");
        notificationsArray.add("\n"+"Let That Man-go"+"\n"+"Pickup Time: 6 PM"+"\n");
        notificationsArray.add("\n"+"Nacho Problem"+"\n"+"Pickup Time: 7 PM"+"\n");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notificationsArray);
        notificationsListView.setAdapter(arrayAdapter);

        notificationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(viewNotifications.this, notifications_expanded.class);
                intent.putExtra("itemClicked", notificationsArray.get(i).toString());
                startActivity(intent);
            }
        });
    }
}
