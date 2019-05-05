package com.example.final_foodweb_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class donations_expanded extends AppCompatActivity {

    private ImageView foodPic;
    private TextView foodName, foodAmount;

    //Plan is to make this the same as pickups expanded


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        final String info = extras.getString("donation");
        String[] infoSplit = info.split("\n");

        /** KEY NOTE: infoSplit[1] is the organization name and infoSplit[2] is the pickup time! **/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donations_expanded);

        foodPic = findViewById(R.id.foodPic);
        foodName = findViewById(R.id.foodName);
        foodAmount = findViewById(R.id.foodAmount);

        foodName.setText(infoSplit[1]);
        foodAmount.setText(infoSplit[2]);
        System.out.println(foodAmount.getText());
        foodPic.setImageDrawable(getResources().getDrawable(R.drawable.cratepage));
    }
}
