package com.example.final_foodweb_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class OrganizationCreate extends AppCompatActivity {

    private Button create_account;
    private EditText email,password,organizationname,address, phonenumber;
    FirebaseDatabase database;
    DatabaseReference account;
    public static final int REQUEST_TAKE_PHOTO = 1;
    private Bitmap imageBitmap;
    private Button chooselogo;
    private int uploadedLogo = 0;

    FirebaseDatabase t_database;
    DatabaseReference organization_ref,donator_ref;

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
            uploadedLogo = 1;
            Toast.makeText(OrganizationCreate.this, "Logo Uploaded!.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("donation");
        create_account  = findViewById(R.id.createaccount);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        organizationname = findViewById(R.id.organizationname);
        address = findViewById(R.id.address);
        chooselogo = findViewById(R.id.chooselogo);
        phonenumber = findViewById(R.id.phonenumber);

        database = FirebaseDatabase.getInstance();
//        UUID uuid = UUID.randomUUID();
//        account = database.getReference(uuid.toString());

        organization_ref = database.getReference("Organizations");
//        account = organization_ref.child(uuid.toString());
//        donator_ref = database.getReference("Donators");

        chooselogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationCreate.this, OrganizationLogin.class);
                intent.putExtra("hello","passing");

                account = organization_ref.child(""+email.getText().toString().hashCode());

                DatabaseReference a_email = account.child("user_email");
                DatabaseReference a_password = account.child("user_password");
                DatabaseReference a_org_name = account.child("user_organization");
                DatabaseReference a_address = account.child("user_address");
                DatabaseReference a_phonenumber = account.child("phone_number");
                DatabaseReference pic = account.child("Logo");

                a_email.setValue(email.getText().toString());
                a_password.setValue(password.getText().toString());
                a_org_name.setValue(organizationname.getText().toString());
                a_address.setValue(address.getText().toString());
                a_phonenumber.setValue(phonenumber.getText().toString());

                /** Starting image saving **/
                ByteArrayOutputStream baos = new ByteArrayOutputStream();


                if (uploadedLogo == 0) {
                    ImageView imageView = new ImageView(OrganizationCreate.this);
                    imageView.setImageResource(R.drawable.defaultlogo);
                    Bitmap defaultBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    defaultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                } else{
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                }

                byte[] imagebytes = baos.toByteArray();
                String imageString = Base64.encodeToString(imagebytes, Base64.DEFAULT);

                // get the picture of food
                pic.setValue(imageString);

                startActivity(intent);
            }
        });

    }
}
