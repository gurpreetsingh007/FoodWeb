package com.example.final_foodweb_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class addItem extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;

    private ImageView foodPic;
    private Bitmap imageBitmap;
    private Button addItem;
    private EditText foodName, foodAmount;

    /** ------------------------ ALL PICTURE STUFF ------------------------------- **/

    /** TAKES THE PICTURE **/
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            foodPic.setImageBitmap(imageBitmap);
        }
    }
    /** ------------------------------------------------------------------------- **/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_donation);

        foodPic = (ImageView) findViewById(R.id.foodPic);
        addItem = (Button) findViewById(R.id.addButton);
        foodAmount = findViewById(R.id.foodAmount);
        foodName = findViewById(R.id.foodName);

        /** When you click the photoButton, it should take a photo. **/
        foodPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foodName.getText().toString().isEmpty() || foodAmount.getText().toString().isEmpty()) {
                    Toast.makeText(addItem.this, "Fill in all information.", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(addItem.this, DonatorInfo.class);
                    intent.putExtra("AddItemDone","finished");
                    startActivity(intent);
                    Toast.makeText(addItem.this, "Item Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
