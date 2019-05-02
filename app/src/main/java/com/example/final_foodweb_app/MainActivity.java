package com.example.final_foodweb_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private Button organization,food_donator;
    FirebaseDatabase database;
    DatabaseReference organization_ref,donator_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        organization = findViewById(R.id.organization_button);
        food_donator = findViewById(R.id.donator_button);
//        database = FirebaseDatabase.getInstance();
//        organization_ref = database.getReference("Organizations");
//        donator_ref = database.getReference("Donators");
        organization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrganizationLogin.class);
                intent.putExtra("donation","passing");
                startActivity(intent);
            }
        });
        food_donator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, DonateLogin.class);
                intent2.putExtra("donation","passing");
                startActivity(intent2);
            }
        });
    }
}
