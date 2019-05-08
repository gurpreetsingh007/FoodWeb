package com.example.final_foodweb_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class viewNotifications extends AppCompatActivity {

    ListView notificationsListView;
    FirebaseDatabase database;
    List<String> orgnames = new ArrayList<>();
    List<String> restnames = new ArrayList<>();
    ArrayList<String> notificationsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);

        notificationsListView = findViewById(R.id.notificationsList);

        //connect to DB
        database = FirebaseDatabase.getInstance();
        DatabaseReference orgRef = database.getReference("Organizations");

        //pull from DB
        ValueEventListener orgValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterchildren = children.iterator();
                while (iterchildren.hasNext()) {
                    DataSnapshot data = iterchildren.next();
                    Map info = (HashMap) data.getValue();
                    String orgname = (String) info.get("user_organization");
                    if (!notificationsArray.contains(orgname)) {
                        notificationsArray.add(orgname);
                    }
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(viewNotifications.this, android.R.layout.simple_list_item_1,notificationsArray);
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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("0", "cancelled");
            }
        };
        orgRef.addValueEventListener(orgValueEventListener);
        //Log.d("0", "Orgnames After:\n"+orgnames);

//        DatabaseReference donorRef = database.getReference("Donators");
//        ValueEventListener restValueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                Iterator<DataSnapshot> iterchildren = children.iterator();
//                while (iterchildren.hasNext()) {
//                    DataSnapshot data = iterchildren.next();
//                    Map info = (HashMap) data.getValue();
//                    //Log.d("0", "Info\n"+info);
//                    String restname = (String) info.get("user_organization");
//                    if (!restnames.contains(restnames)) {
//                        restnames.add(restname);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.d("0", "cancelled");
//            }
//        };
//        donorRef.addValueEventListener(restValueEventListener);

//        Log.d("0", "Orgnames:\n"+orgnames);
//        Log.d("0", "Restnames:\n"+restnames);

        //Log.d("0", "Notification Array\n"+notificationsArray);

        //notificationsArray.add("\n" + "Jacobs Pantry Demo ");

//        notificationsArray.add("\n"+"Student Org 1"+"\n"+"Pickup Time: 1 PM"+"\n");
//        notificationsArray.add("\n"+"Food Collective"+"\n"+"Pickup Time: 2 PM"+"\n");
//        notificationsArray.add("\n"+"Student Org 2"+"\n"+"Pickup Time: 3 PM"+"\n");
//        notificationsArray.add("\n"+"Cheese Louise"+"\n"+"Pickup Time: 4 PM"+"\n");
//        notificationsArray.add("\n"+"Take a Pizza Me"+"\n"+"Pickup Time: 5 PM"+"\n");
//        notificationsArray.add("\n"+"Let That Man-go"+"\n"+"Pickup Time: 6 PM"+"\n");
//        notificationsArray.add("\n"+"Nacho Problem"+"\n"+"Pickup Time: 7 PM"+"\n");
    }
}
