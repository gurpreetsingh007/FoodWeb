package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class pickups_expanded extends AppCompatActivity {

    private TextView orgNameP, pickupTimeP, rescheduleText;
    private Button reschedule;
    private Button pickedUpItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //need to update to have format (top-down): logo, restaurant/pantry name, address, contact info, selected food, time, cancel
        Bundle extras = getIntent().getExtras();
        final String info = extras.getString("itemClicked");
        String[] infoSplit = info.split("\n");

        /** KEY NOTE: infoSplit[1] is the organization name and infoSplit[2] is the pickup time! **/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_expanded);

        pickedUpItem = findViewById(R.id.pickedUpItem);
        pickedUpItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pickups_expanded.this, OrganizationInfo.class);
                startActivity(intent);
                Toast.makeText(pickups_expanded.this, "Item claimed!", Toast.LENGTH_SHORT).show();
            }
        });

//        orgNameP = findViewById(R.id.orgNameP);
//        pickupTimeP = findViewById(R.id.pickupTimeP);
//        reschedule = findViewById(R.id.reschedule);
//        rescheduleText = findViewById(R.id.rescheduleText);
//
//        orgNameP.setText(infoSplit[1]);
//        pickupTimeP.setText("Current "+infoSplit[2]);
//
//        reschedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(pickups_expanded.this, viewPickups.class);
//                intent.putExtra("newTime", rescheduleText.getText().toString());
//                intent.putExtra("orgName", orgNameP.toString());
//                viewPickups.wasClicked = true;
//                startActivity(intent);
//                Toast.makeText(pickups_expanded.this, "Pickup rescheduled!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
