package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDonations extends AppCompatActivity {
    ListView donationsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_donations);

        donationsListView = findViewById(R.id.donationsList);

        final ArrayList<String> donationsArray = new ArrayList<>();

        //connect DB
        //pull from DB here

//        donationsArray.add("\n"+"Student Org 1"+"\n"+"Date: April 25, 2019"+"\n");
//        donationsArray.add("\n"+"Food Collective"+"\n"+"Date: April 16, 2019"+"\n");
//        donationsArray.add("\n"+"Student Org 2"+"\n"+"Date: April 10, 2019"+"\n");
//        donationsArray.add("\n"+"Cheese Louise"+"\n"+"Date: March 24, 2019"+"\n");
//        donationsArray.add("\n"+"Take a Pizza Me"+"\n"+"Date: March 2, 2019"+"\n");
//        donationsArray.add("\n"+"Let That Man-go"+"\n"+"Date: January 7, 2019"+"\n");
//        donationsArray.add("\n"+"Nacho Problem"+"\n"+"Date: November 19, 2018"+"\n");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,donationsArray);
        donationsListView.setAdapter(arrayAdapter);

        donationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewDonations.this, donations_expanded.class);
                intent.putExtra("donation", donationsArray.get(i).toString());
                startActivity(intent);
            }
        });
    }
}
