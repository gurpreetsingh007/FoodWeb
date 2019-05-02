package com.example.final_foodweb_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class OrganizationLogin extends AppCompatActivity {


    private Button login,create_account;
    String username,password;
    EditText e_username,e_password;
    FirebaseDatabase database;
    boolean passwordverification;
    boolean emailverification;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_login);
        Intent intent = getIntent();
        String passing = intent.getStringExtra("donation");

        login = findViewById(R.id.login);
        create_account = findViewById(R.id.CreateAccount);
        e_username = findViewById(R.id.email);
        e_password = findViewById(R.id.password);
        username = e_username.getText().toString();
        password = e_password.getText().toString();

//        Log.d("0","OUTSIDEusername\n\n"+username);
//        Log.d("0","OUTSIDEpassword\n\n"+password);
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
        emailverification = false;
        passwordverification= false;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("0","username\n\n"+username);
//                Log.d("0","password\n\n"+password);
                username = e_username.getText().toString();
                password = e_password.getText().toString();

                Log.d("0","username\n\n"+username.length());
                Log.d("0","password\n\n"+password.length());
                if(username.length()>0 && password.length()>0){
//                    emailverification = false;
//                    passwordverification= false;
                    database = FirebaseDatabase.getInstance();
                    DatabaseReference accountref = database.getReference("Organizations");
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                            Iterator<DataSnapshot> iterchildren = children.iterator();
                            while(iterchildren.hasNext()){
                                DataSnapshot data = iterchildren.next();
                                Map info = (HashMap) data.getValue();
                                //verify email and password from database
                                if((""+username.hashCode()).equals(data.getKey())){
                                    Log.d("0","email true \n");
                                    emailverification = true;
                                }
                                if(info.get("user_password").equals(password)){
                                    Log.d("0","password true \n");
                                    passwordverification= true;
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("0", "cancelled");
                        }
                    };
                    accountref.addValueEventListener(valueEventListener);
                    Log.d("0","emailverification = \n\n"+emailverification);
                    Log.d("0","passwordverification = \n\n"+passwordverification);
                    if(emailverification && passwordverification){
                        Intent intent = new Intent(OrganizationLogin.this, OrganizationInfo.class);
                        intent.putExtra("login","passing");
                        startActivity(intent);
                    }
                    else if (!emailverification){
                        Toast.makeText(OrganizationLogin.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    }
                    else if(!passwordverification){
                        Toast.makeText(OrganizationLogin.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(OrganizationLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verifyaccount(){
        database = FirebaseDatabase.getInstance();
        DatabaseReference accountref = database.getReference("Organizations");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterchildren = children.iterator();
                while(iterchildren.hasNext()){
                    DataSnapshot data = iterchildren.next();
                    Map info = (HashMap) data.getValue();
                    //verify email and password from database
                    if((""+username.hashCode()).equals(data.getKey())){
                        Log.d("0","email true \n");
                        emailverification = true;
                    }
                    if(info.get("user_password").equals(password)){
                        Log.d("0","password true \n");
                        passwordverification= true;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("0", "cancelled");
            }
        };
        accountref.addValueEventListener(valueEventListener);
    }

}
