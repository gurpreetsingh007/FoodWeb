package com.example.final_foodweb_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class OrganizationPickups extends AppCompatActivity {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Double> mtime = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organizationpickups);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("pickups");
        initImageBitmaps();

    }

    private void initImageBitmaps(){
//        mImageUrls.add("https://i.redd.it/mgbymkbaleu21.jpg");
//        mNames.add("Tandori night");
//        mtime.add(2.3);
//
//        mImageUrls.add("https://i.redd.it/4qy2aygceau21.jpg");
//        mNames.add("Taco bell");
//        mtime.add(3.1);
//
//        mImageUrls.add("https://i.redd.it/nu6sdx4ydiu21.jpg");
//        mNames.add("Chaat cafe");
//        mtime.add(1.5);
//
//        mImageUrls.add("https://i.redd.it/zmio0th7eeu21.jpg");
//        mNames.add("Thai Noodles");
//        mtime.add(2.1);
//
//        mImageUrls.add("https://i.redd.it/c0xkavo6egu21.jpg");
//        mNames.add("Viks");
//        mtime.add(4.1);
//
//        mImageUrls.add("https://i.redd.it/6g0wx2otugu21.jpg");
//        mNames.add("Mehak chaat");
//        mtime.add(0.5);

        //Pull from DB
        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.pickup_recycler_view);
        Pickup_Adapter adapter = new Pickup_Adapter(this,mNames,mImageUrls,mtime);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
