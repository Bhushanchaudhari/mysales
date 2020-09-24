package com.example.mysales;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mysales.ui.Sales;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {



    EditText txtFullname, txtUserName, txtPassword, txtEmail;
    Button btn_register;
    RadioButton radioGenderMale, radioGenderFemale;


    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       // Objects.requireNonNull(getSupportActionBar()).setTitle("Sign Up");

        txtFullname = (EditText) findViewById(R.id.txtName);
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtEmail =(EditText)  findViewById(R.id.txtEmail);
        txtPassword =(EditText)  findViewById(R.id.txtPassword);
        btn_register =(Button) findViewById(R.id.btn_register);
        radioGenderFemale = (RadioButton) findViewById(R.id.radioGenderFemale);
        radioGenderMale = (RadioButton) findViewById(R.id.radioGenderMale);



        databaseReference = FirebaseDatabase.getInstance().getReference("Sales");

        firebaseAuth = FirebaseAuth.getInstance();



        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                  final String gender;

                 final String fullName = txtFullname.getText().toString();
                 final String userName = txtUserName.getText().toString();
                 final String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (radioGenderMale.isChecked()){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(Signup.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                }
                 else if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Signup.this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Sales information = new Sales(
                                            fullName,
                                            userName,
                                            email,
                                            gender
                                    );


                                    FirebaseDatabase.getInstance().getReference("Sales")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    Toast.makeText(Signup.this, "Register Successfully",
                                                            Toast.LENGTH_SHORT).show();

                                                    startActivity(new Intent(getApplicationContext(), Dashboard.class));

                                                }
                                            });


                                } else {

                                    Toast.makeText(Signup.this, "Failed !!!", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            }
        });



    }

}