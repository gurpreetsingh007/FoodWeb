package com.example.final_foodweb_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button organization,food_donator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        organization = findViewById(R.id.organization_button);
        food_donator = findViewById(R.id.donator_button);
        food_donator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DonatorSide.class);
                intent.putExtra("donation","passing");
                startActivity(intent);
            }
        });

    }
}
