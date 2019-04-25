package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OrganizationLogin extends AppCompatActivity {


    private Button login,create_account;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_login);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("donation");

        login = findViewById(R.id.login);
        create_account = findViewById(R.id.CreateAccount);


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationLogin.this, OrganizationCreate.class);
                intent.putExtra("donation","passing");
                startActivity(intent);
            }
        });
        // after account creation come back to login
        Intent intent_1 = getIntent();
        String getting = intent_1.getStringExtra("hello");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationLogin.this, OrganizationInfo.class);
                intent.putExtra("login","passing");
                startActivity(intent);

            }
        });



    }

}
