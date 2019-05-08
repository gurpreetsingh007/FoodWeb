package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class DonatorCreate extends AppCompatActivity {

    private Button create_account,logo;
    private EditText email,password,organizationname,address,phonenumber;
    FirebaseDatabase database;
    DatabaseReference account;

    FirebaseDatabase t_database;
    DatabaseReference organization_ref,donator_ref;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("donation");
        create_account  = findViewById(R.id.createaccount);
        logo = findViewById(R.id.chooselogo);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        organizationname = findViewById(R.id.organizationname);
        address = findViewById(R.id.address);

        database = FirebaseDatabase.getInstance();
        organization_ref = database.getReference("Donators");
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonatorCreate.this, DonateLogin.class);
                intent.putExtra("hello","passing");

                account = organization_ref.child(""+email.getText().toString().hashCode());

                DatabaseReference a_email = account.child("user_email");
                DatabaseReference a_password = account.child("user_password");
                DatabaseReference a_org_name = account.child("user_organization");
                DatabaseReference a_address = account.child("user_address");
                DatabaseReference a_phonenumber = account.child("user_phonenumber");



                a_email.setValue(email.getText().toString());
                a_password.setValue(password.getText().toString());
                a_org_name.setValue(organizationname.getText().toString());
                a_address.setValue(address.getText().toString());
                if(phonenumber.getText().toString().length() == 10 ){
                    a_phonenumber.setValue(phonenumber.getText().toString());
                }
                else if (phonenumber.getText().toString().length() > 10 || phonenumber.getText().toString().length()<10 ){
                    Toast.makeText(DonatorCreate.this, "Invalid phone, skip it?", Toast.LENGTH_SHORT).show();
                }
                else{
                    a_phonenumber.setValue("N/A");
                }

                startActivity(intent);
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // need to do some work here, send to another activity and choose default logo or the
                // picture taken one
            }
        });

    }
}
