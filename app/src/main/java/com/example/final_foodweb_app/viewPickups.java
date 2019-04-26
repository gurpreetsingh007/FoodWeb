package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class viewPickups extends AppCompatActivity {

    ListView notificationsListView;
    private String newTime, orgName;

    public static Boolean wasClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);

        if (wasClicked) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            newTime = extras.get("newTime").toString();
            orgName = extras.get("orgName").toString();
        } else {
            orgName = null;
        }

        notificationsListView = findViewById(R.id.notificationsList);

        final ArrayList<String> notificationsArray = new ArrayList<>();

        String temp = "\n"+"Tandoori Nite"+"\n"+"Pickup Time: 1 PM"+"\n";
        String[] tempsplit = temp.split("\n");

        if (orgName != null && orgName.equals(tempsplit[1])) {
            temp = "\n"+orgName+"\n"+"Pickup Time: "+newTime+"\n";
        }

        notificationsArray.add(temp);

        notificationsArray.add("\n"+"Restaurant 2"+"\n"+"Pickup Time: 2 PM"+"\n");
        notificationsArray.add("\n"+"Cheeseboard"+"\n"+"Pickup Time: 3 PM"+"\n");
        notificationsArray.add("\n"+"Cheese Louise"+"\n"+"Pickup Time: 4 PM"+"\n");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notificationsArray);
        notificationsListView.setAdapter(arrayAdapter);

        notificationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(viewPickups.this, pickups_expanded.class);
                intent.putExtra("itemClicked", notificationsArray.get(i).toString());
                System.out.println(notificationsArray.get(i).toString());
                startActivity(intent);
            }
        });
    }
}
