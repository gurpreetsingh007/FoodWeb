package com.example.final_foodweb_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class ViewDonations extends AppCompatActivity {
    ListView donationsListView;
    String username;
    ArrayList<String> donationsArray = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference databaseReference,account;
    ImageView imageView;

    private ArrayList<String> foodnames = new ArrayList<>();
    private ArrayList<String> foodquantities = new ArrayList<>();
    private ArrayList<Bitmap> foodimages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_donations);
        Intent intent = getIntent();
        username = intent.getStringExtra("donations");
        database = FirebaseDatabase.getInstance();
        String s = ""+username.hashCode();
        databaseReference = database.getReference("Donators/"+s+"/Food items");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterchildren = children.iterator();
                while(iterchildren.hasNext()){
                    DataSnapshot data = iterchildren.next();
                    Map info = (HashMap) data.getValue();
                    Log.d("0","hashmap"+ info);
                    String foodname = info.get("Food name").toString();
                    String foodquantity = (String)info.get("Food quantity");
                    String foodpic = (String) info.get("Food pic");
                    byte[] imagebytes = Base64.decode(foodpic,Base64.DEFAULT);
                    Bitmap decodeImage = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
//                    imageView.setImageResource(decodeImage);

                    Log.d("0","Decode\n\n"+decodeImage);
                    foodnames.add(foodname);
                    foodquantities.add(foodquantity);
                    foodimages.add(decodeImage);
                    initRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);


        //connect DB
        //pull from DB here

//        donationsArray.add("\n"+"Student Org 1"+"\n"+"Date: April 25, 2019"+"\n");
//        donationsArray.add("\n"+"Food Collective"+"\n"+"Date: April 16, 2019"+"\n");
//        donationsArray.add("\n"+"Student Org 2"+"\n"+"Date: April 10, 2019"+"\n");
//        donationsArray.add("\n"+"Cheese Louise"+"\n"+"Date: March 24, 2019"+"\n");
//        donationsArray.add("\n"+"Take a Pizza Me"+"\n"+"Date: March 2, 2019"+"\n");
//        donationsArray.add("\n"+"Let That Man-go"+"\n"+"Date: January 7, 2019"+"\n");
//        donationsArray.add("\n"+"Nacho Problem"+"\n"+"Date: November 19, 2018"+"\n");


//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,donationsArray);
//        donationsListView.setAdapter(arrayAdapter);
//
//        donationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ViewDonations.this, donations_expanded.class);
//                intent.putExtra("donation", donationsArray.get(i).toString());
//                startActivity(intent);
//            }
//        });
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.donations_my_recycler_view);
        ViewDonationsAdapter adapter = new ViewDonationsAdapter(this,foodnames,foodimages,foodquantities);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
