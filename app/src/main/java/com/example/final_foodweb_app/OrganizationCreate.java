package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OrganizationCreate extends AppCompatActivity {

    private Button create_account;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("donation");
        create_account  = findViewById(R.id.createaccount);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationCreate.this, OrganizationLogin.class);
                intent.putExtra("hello","passing");
                startActivity(intent);
            }
        });

    }
}
