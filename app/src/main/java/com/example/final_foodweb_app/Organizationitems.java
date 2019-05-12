package com.example.final_foodweb_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Organizationitems extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset;
    private String username, hashto;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Bitmap> mImageUrls = new ArrayList<>();
//    private ArrayList<Double> mdistance = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference databaseReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organizationitems);
        Intent intent = getIntent();
        username = intent.getStringExtra("items");
        hashto = intent.getStringExtra("hashto");
        initImageBitmaps();

    }

    private void initImageBitmaps(){


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Donators");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterchildren = children.iterator();
                while(iterchildren.hasNext()){
                    DataSnapshot data = iterchildren.next();
                    Map info = (HashMap) data.getValue();
                    String org_name = info.get("user_organization").toString();
                    mNames.add(org_name);

                    Object foodpic = info.get("Logo");
                    String sFoodPic;

                    if (foodpic == null) {
                        //Encode
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageView imageView = new ImageView(Organizationitems.this);
                        imageView.setImageResource(R.drawable.defaultlogo);
                        Bitmap defaultBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                        defaultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imagebytes = baos.toByteArray();
                        sFoodPic = Base64.encodeToString(imagebytes, Base64.DEFAULT);
                    } else {
                        sFoodPic = foodpic.toString();
                    }

                    byte[] imagebytes = Base64.decode(sFoodPic,Base64.DEFAULT);
                    Bitmap decodeImage = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
                    mImageUrls.add(decodeImage);
                    initRecyclerView();



//                    mImageUrls.add("https://i.redd.it/mgbymkbaleu21.jpg");
//                    mdistance.add(10.1);
//                    initRecyclerView();
//                    Log.d("0","Hashmap\n"+info);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
        //Pull from DB
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        MyAdapter adapter = new MyAdapter(this,mNames,mImageUrls,username, hashto);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
