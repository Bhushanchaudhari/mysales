package com.example.mysales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Log in");

    }
    public void btn_login(View view){
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
    }
    public void btn_signup (View view){
        startActivity(new Intent(getApplicationContext(), Signup.class));
    }
}