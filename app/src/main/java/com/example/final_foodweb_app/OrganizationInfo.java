package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OrganizationInfo extends AppCompatActivity {
    private Button available_items, view_pickups, logout;
    private String username, hashto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organizationinfo);
        Intent intent = getIntent();
        username = intent.getStringExtra("login");
        hashto = intent.getStringExtra("hashto");
        available_items = findViewById(R.id.available_button);
        view_pickups = findViewById(R.id.view_pickups);
        logout = findViewById(R.id.logout);
        available_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationInfo.this, Organizationitems.class);
                intent.putExtra("items",username);
                intent.putExtra("hashto", hashto);
                startActivity(intent);
            }
        });

        view_pickups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationInfo.this, viewPickups.class);
                intent.putExtra("viewPickups","passing");
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationInfo.this, MainActivity.class);
                intent.putExtra("logout","passing");
                startActivity(intent);
            }
        });




    }
}
