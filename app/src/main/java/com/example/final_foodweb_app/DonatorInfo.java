package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DonatorInfo extends AppCompatActivity {
    private ImageButton addDonationButton, viewNotificationsButton, viewAllDonationsButton;
    private TextView addDonationText, viewNotificationsText, viewAllDonationsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donator_info);

        //get all vars
        addDonationButton = findViewById(R.id.addDonationButton);
        viewNotificationsButton = findViewById(R.id.viewNotificationsButton);
        viewAllDonationsButton = findViewById(R.id.viewAllDonationsButton);

        addDonationText = findViewById(R.id.addDonationsText);
        viewNotificationsText = findViewById(R.id.viewNotificationsText);



        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonatorInfo.this, addItem.class);
                intent.putExtra("addItem","donation button clicked");
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
                System.out.println("Add me!");
            }
        });

    }
}