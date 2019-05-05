package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DonatorInfo extends AppCompatActivity {
    private ImageView addDonationButton, viewNotificationsButton, viewAllDonationsButton;
    private TextView addDonationText, viewNotificationsText, viewAllDonationsText;
    private Button logout;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donator_info);
        Intent intent = getIntent();
        username = intent.getStringExtra("login");


        //get all vars
        addDonationButton = findViewById(R.id.addDonationButton);
        viewNotificationsButton = findViewById(R.id.viewNotificationsButton);
        viewAllDonationsButton = findViewById(R.id.viewAllDonationsButton);

        addDonationText = findViewById(R.id.addDonationsText);
        viewNotificationsText = findViewById(R.id.viewNotificationsText);
        viewAllDonationsText = findViewById(R.id.viewAllDonationsText);
        logout = findViewById(R.id.logout);


        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonatorInfo.this, addItem.class);
                intent.putExtra("addItem",username);
                startActivity(intent);
            }
        });
        addDonationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonatorInfo.this, addItem.class);
                intent.putExtra("addItem","donation button clicked");
                startActivity(intent);
            }
        });


        viewNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonatorInfo.this, viewNotifications.class);
                intent.putExtra("notifications","viewing notifications");
                startActivity(intent);
            }
        });
        viewNotificationsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonatorInfo.this, viewNotifications.class);
                intent.putExtra("notifications","viewing notifications");
                startActivity(intent);
            }
        });



        viewAllDonationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonatorInfo.this, ViewDonations.class);
                intent.putExtra("donations","viewing donations");
                startActivity(intent);
            }
        });
        viewAllDonationsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonatorInfo.this, ViewDonations.class);
                intent.putExtra("donations","viewing donations");
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonatorInfo.this, MainActivity.class);
                intent.putExtra("donations","viewing donations");
                startActivity(intent);
            }
        });

    }
}
