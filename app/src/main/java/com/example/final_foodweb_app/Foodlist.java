package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Foodlist extends AppCompatActivity {


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Double> mQuantities = new ArrayList<>();
    private String defaultlogo = "https://i.redd.it/mgbymkbaleu21.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("items");
        initImageBitmaps();



    }

    private void initImageBitmaps() {
//        mImageUrls.add("https://i.redd.it/mgbymkbaleu21.jpg");
//        mNames.add("7-layer Burrito");
//        mQuantities.add(4.3);
//
//        mImageUrls.add("https://i.redd.it/4qy2aygceau21.jpg");
//        mNames.add("Tacos");
//        mQuantities.add(3.1);
//
//        mImageUrls.add("https://i.redd.it/nu6sdx4ydiu21.jpg");
//        mNames.add("Nacho Fries");
//        mQuantities.add(1.5);
//
//        mImageUrls.add("https://i.redd.it/zmio0th7eeu21.jpg");
//        mNames.add("Hash Browns");
//        mQuantities.add(5.1);
//
//        mImageUrls.add("https://i.redd.it/c0xkavo6egu21.jpg");
//        mNames.add("Cheesy Roll-Up");
//        mQuantities.add(4.1);
//
//        mImageUrls.add("https://i.redd.it/6g0wx2otugu21.jpg");
//        mNames.add("Bean Burrito");
//        mQuantities.add(3.5);
//        initRecyclerView();

        //ADD CODE HERE FOR DB FUNCTIONALITY

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.food_my_recycler_view);
        FoodlistAdapter adapter = new FoodlistAdapter(this,mNames,mImageUrls,mQuantities);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
