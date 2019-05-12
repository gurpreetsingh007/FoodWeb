package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class notifications_expanded extends AppCompatActivity {

    private TextView orgName, pickupTime;
    private Button pickedUpItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Could be depricated, will use pickups expanded instead

        Bundle extras = getIntent().getExtras();
        final String info = extras.getString("itemClicked");
        String[] infoSplit = info.split("\n");

        /** KEY NOTE: infoSplit[1] is the organization name and infoSplit[2] is the pickup time! **/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_expanded);

        orgName = findViewById(R.id.orgName);
        pickupTime = findViewById(R.id.pickupTime);
        pickedUpItem = findViewById(R.id.pickedUpItem);
//
//        orgName.setText(infoSplit[1]);
//        pickupTime.setText(infoSplit[2]);

        pickedUpItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notifications_expanded.this, DonatorInfo.class);
                startActivity(intent);
                Toast.makeText(notifications_expanded.this, "Item claimed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
