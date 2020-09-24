package com.example.mysales;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        getSupportActionBar().hide();


        //------------Navigation drawer code-----------
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        getSupportActionBar().hide();
                        if(menuItem.getItemId()== R.id.nav_home){
                            Toast.makeText(Dashboard.this, "Home", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AddSalesOrder.class));
                        }
                        DrawerLayout drawerLayout= findViewById(R.id.drawer_layout);
                        drawerLayout.closeDrawer(GravityCompat.START);

                        return true;
                    }
                });



    }
    public void btn_signup(View view){
        startActivity(new Intent(getApplicationContext(),Signup.class));
    }
}