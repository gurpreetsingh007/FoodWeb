package com.example.final_foodweb_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Foodlist extends AppCompatActivity {


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Bitmap> mImageUrls = new ArrayList<>();
    private ArrayList<String> mQuantities = new ArrayList<>();
    private ArrayList<String> hashingfood = new ArrayList<>();

    private String defaultlogo = "https://i.redd.it/mgbymkbaleu21.jpg";
    private String username, hashto;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        Intent intent = getIntent();
        username = intent.getStringExtra("items");
        //username outputs restaurant you selected
        hashto = intent.getStringExtra("hashto");
        System.out.println("From foodlist: "+"hashto is: "+ hashto);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Donators");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterchildren = children.iterator();
                while(iterchildren.hasNext()){
                    DataSnapshot data = iterchildren.next();
                    Map info = (HashMap) data.getValue();
                    String org_name = info.get("user_organization").toString();
                    if(org_name.equals(username)){
                        Map food = (HashMap) info.get("Food items");
                        Set a = food.keySet();
                        Iterator<String> b = a.iterator();
                        while(b.hasNext()){
                            String c = b.next();
                            hashingfood.add(c);
                        }
                        for(int i=0; i < hashingfood.size();i++){
                            Map fooditems = (HashMap) food.get(hashingfood.get(i));
                            mNames.add((String) fooditems.get("Food name"));
                            mQuantities.add((String) fooditems.get("Food quantity"));

                            String foodpic = (String) fooditems.get("Food pic");
                            byte[] imagebytes = Base64.decode(foodpic,Base64.DEFAULT);
                            Bitmap decodeImage = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
                            mImageUrls.add(decodeImage);
                            initRecyclerView();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


//        additems.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent currintent = getIntent();
//                Bundle extras = currintent.getExtras();
//                String name = (String) extras.get("name");
//
//                if (name == null){
//                    Toast.makeText(Foodlist.this, "Select an item.", Toast.LENGTH_SHORT).show();
//                } else {
//                    database = FirebaseDatabase.getInstance();
//                    String s = ""+username.hashCode();
//                    databaseReference = database.getReference("Organizations/"+s);
//                    DatabaseReference r_menu = databaseReference.child("Selected Items");
//                    DatabaseReference items = r_menu.child("Name");
//                    items.setValue(name);
//                }
//
//            }
//        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.food_my_recycler_view);
        FoodlistAdapter adapter = new FoodlistAdapter(this,mNames,mImageUrls,mQuantities,hashto,username);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
