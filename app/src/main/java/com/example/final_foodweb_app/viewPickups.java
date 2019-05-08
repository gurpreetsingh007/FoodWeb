package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class viewPickups extends AppCompatActivity {

    ListView notificationsListView;
    private String newTime, orgName;
    FirebaseDatabase database;

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

        database = FirebaseDatabase.getInstance();
        DatabaseReference restRef = database.getReference("Donators");

        ValueEventListener restValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterchildren = children.iterator();
                while (iterchildren.hasNext()) {
                    DataSnapshot data = iterchildren.next();
                    Map info = (HashMap) data.getValue();
                    String restname = (String) info.get("user_organization");
                    if (!notificationsArray.contains(restname)) {
                        notificationsArray.add(restname);
                    }
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(viewPickups.this, android.R.layout.simple_list_item_1,notificationsArray);
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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("0", "cancelled");
            }
        };
        restRef.addValueEventListener(restValueEventListener);

//        String temp = "\n"+"Tandoori Nite"+"\n"+"Pickup Time: 1 PM"+"\n";
//        String[] tempsplit = temp.split("\n");
//
//        if (orgName != null && orgName.equals(tempsplit[1])) {
//            temp = "\n"+orgName+"\n"+"Pickup Time: "+newTime+"\n";
//        }
//
//        notificationsArray.add(temp);
//
//        notificationsArray.add("\n"+"Restaurant 2"+"\n"+"Pickup Time: 2 PM"+"\n");
//        notificationsArray.add("\n"+"Cheeseboard"+"\n"+"Pickup Time: 3 PM"+"\n");
//        notificationsArray.add("\n"+"Cheese Louise"+"\n"+"Pickup Time: 4 PM"+"\n");

        //connect and pull from DB
    }
}
