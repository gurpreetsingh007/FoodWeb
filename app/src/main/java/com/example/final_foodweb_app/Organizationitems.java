package com.example.final_foodweb_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Organizationitems extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Double> mdistance = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organizationitems);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("items");
        initImageBitmaps();

    }

    private void initImageBitmaps(){
//        mImageUrls.add("https://i.redd.it/mgbymkbaleu21.jpg");
//        mNames.add("Tandori night");
//        mdistance.add(2.3);
//
//        mImageUrls.add("https://i.redd.it/4qy2aygceau21.jpg");
//        mNames.add("Taco bell");
//        mdistance.add(3.1);
//
//        mImageUrls.add("https://i.redd.it/nu6sdx4ydiu21.jpg");
//        mNames.add("Chaat cafe");
//        mdistance.add(1.5);
//
//        mImageUrls.add("https://i.redd.it/zmio0th7eeu21.jpg");
//        mNames.add("Thai Noodles");
//        mdistance.add(2.1);
//
//        mImageUrls.add("https://i.redd.it/c0xkavo6egu21.jpg");
//        mNames.add("Viks");
//        mdistance.add(4.1);
//
//        mImageUrls.add("https://i.redd.it/6g0wx2otugu21.jpg");
//        mNames.add("Mehak chaat");
//        mdistance.add(0.5);

        //Pull from DB

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        MyAdapter adapter = new MyAdapter(this,mNames,mImageUrls,mdistance);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
