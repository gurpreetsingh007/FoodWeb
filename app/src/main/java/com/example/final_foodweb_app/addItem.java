package com.example.final_foodweb_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

public class addItem extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;

    private ImageView foodPic;
    private Bitmap imageBitmap;
    private Button addItem;
    private EditText foodName, foodAmount;
    FirebaseDatabase database;
    DatabaseReference databaseReference,account;
    private String username;

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
//    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//        DatabaseReference ref = FirebaseDatabase.getInstance()
//                .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child(mRestaurant.getPushId())
//                .child("imageUrl");
//        ref.setValue(imageEncoded);
//    }

    /** ------------------------------------------------------------------------- **/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_donation);
        Intent intent = getIntent();
        username = intent.getStringExtra("addItem");

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
                    database = FirebaseDatabase.getInstance();
                    String s = ""+username.hashCode();
                    databaseReference = database.getReference("Donators/"+s);
                    DatabaseReference r_menu = databaseReference.child("Food items");
                    DatabaseReference hashed = r_menu.child(""+foodName.getText().toString().hashCode());
                    DatabaseReference name = hashed.child("Food name");
                    DatabaseReference quantity = hashed.child("Food quantity");
                    DatabaseReference pic = hashed.child("Food pic");
                    name.setValue(foodName.getText().toString());
                    quantity.setValue(foodAmount.getText().toString());

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap = ((BitmapDrawable) foodPic.getDrawable()).getBitmap();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] imagebytes = baos.toByteArray();
                    String imageString = Base64.encodeToString(imagebytes,Base64.DEFAULT);

                    // get the picture of food
                    pic.setValue(imageString);
                    Log.d("0","picture\n\n"+foodPic);

                    Intent intent = new Intent(addItem.this, DonatorInfo.class);
                    intent.putExtra("foodname",foodName.getText().toString());
                    intent.putExtra("username",username);
                    startActivity(intent);
                    Toast.makeText(addItem.this, "Item Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
