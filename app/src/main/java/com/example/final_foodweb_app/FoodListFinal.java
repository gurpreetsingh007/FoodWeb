package com.example.final_foodweb_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodListFinal extends AppCompatActivity {
    private ImageView foodPicFinal;
    private Button addItemFinal;
    private TextView foodName, amt;
    FirebaseDatabase database;
    DatabaseReference account;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_final);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        foodName = findViewById(R.id.foodName);
        addItemFinal = findViewById(R.id.addItemFinal);

        final String name = (String) extras.get("name");
        final String username = (String) extras.get("username");

        foodName.setText(name);

        addItemFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FoodListFinal.this, "Claimed "+name+"!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(FoodListFinal.this, Foodlist.class);
//                startActivity(intent);
//                database = FirebaseDatabase.getInstance();
//                //Get email somehow
//                String s = ""+username.hashCode();
//                Log.d("0","usernmae\n"+username);
//                account = database.getReference("Organizations/"+s);
//                DatabaseReference items = account.child("Selected Items");
//                items.setValue(name);
//                Toast.makeText(FoodListFinal.this, "Claimed "+name+"!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(FoodListFinal.this, Foodlist.class);
//                startActivity(intent);
            }
        });

    }
}
