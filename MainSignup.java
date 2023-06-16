package com.example.headrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import javax.xml.transform.Result;

public class MainSignup extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword , editTextFullName
            , editTextBirthDate, editTextConfirmPass ;
      Button signup ;
      Button login;
      FirebaseAuth mAuth ;
      ProgressBar progressBar;
      TextView textView;

      FirebaseDatabase db = FirebaseDatabase.getInstance();
      DatabaseReference root = db.getReference().child("Users");







    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), hello_user.class);
            startActivity(intent);
            finish();
        }
    }


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_signup);



        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.password);
        signup = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView2);
        editTextFullName = findViewById(R.id.FullName);
        editTextBirthDate = findViewById(R.id.BirthDate);
        editTextConfirmPass = findViewById(R.id.confirm_Pass);






        TooltipCompat.setTooltipText(editTextPassword, "-lower,upperCase letter\n-special symbol " +
                "and numbers\n-length must be 8" );
        TooltipCompat.setTooltipText(editTextEmail, "example@example.com");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressBar.setVisibility(View.VISIBLE);


                String Email, Password , ConfirmPass, textFullName , textDoB , accountT ="" ;



                //obtain the entered data

                Email = String.valueOf(editTextEmail.getText());
                Password = String.valueOf(editTextPassword.getText());
                textFullName = String.valueOf(editTextFullName.getText());
                textDoB = String.valueOf(editTextBirthDate.getText());
                ConfirmPass = String.valueOf(editTextConfirmPass.getText());

                // to save data on REALTIME DATABASE .
                HashMap<String, String > userMap = new HashMap<>();
                userMap.put("Account Type" , accountT);
                userMap.put("Name" , textFullName);
                userMap.put("Birthdate" , textDoB);
                userMap.put("Email" , Email);


                //push generate a random key
                root.push().setValue(userMap);



                if ( textFullName.isEmpty() && textDoB.isEmpty() && Email.isEmpty()
                        && Password.isEmpty() && accountT.isEmpty() && ConfirmPass.isEmpty()){
                    editTextFullName.setError("FullName is required");
                    editTextBirthDate.setError("Date of Birth is required");
                    editTextEmail.setError("Email is required");
                    editTextPassword.setError("Password is required");
                    editTextPassword.setError("Please Confirm Your Password");

                } else if (textFullName.isEmpty()){
                    editTextFullName.setError("FullName is required");

                } else if (textDoB.isEmpty()) {
                    editTextBirthDate.setError("Date of Birth is required");

                } else if (Email.isEmpty()) {
                    editTextBirthDate.setError("Email is required");

                } else if (Password.isEmpty()) {
                    editTextPassword.setError("Password is required"); }



                else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()&& !Email.isEmpty()) {
                    editTextEmail.setError("Please Enter a Valid Email!\nExample@Example.com"); }

                else if (Password.length() < 8 || Password.length() < 8 ) {
                    editTextPassword.setError("Password length should be at least 8 digits"); }
                  else if (!(Password.matches("(.*[0-9].*)"))) {
                    editTextPassword.setError("Add Number to Your Password!");
                } else if (!(Password.matches("(.*[A-Z].*)"))) {
                    editTextPassword.setError((" Add UpperCase letter to Your Password!"));
                } else if (!(Password.matches("(.*[a-z].*)"))) {
                    editTextPassword.setError((" Add LowerCase letter to Your Password!"));
                } else if (!(Password.matches("^(?=.*[_.()$&@]).*&"))) {
                    editTextPassword.setError((" Add Special Symbol to Your Password!"));

                    
                } else if (Password.matches(ConfirmPass)==false) {
                        editTextConfirmPass.setError("Your Confirm Password Must Match the Password!");
                } else {

                    progressBar.setVisibility(View.VISIBLE);

                    Intent intent = new Intent(getApplicationContext(), App_Intro.class);
                    startActivity(intent);
                    finish();


                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);


                                    //sendEmailVerification.
                                    if (task.isSuccessful()) {

                                        auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {


                                                    editTextEmail.setText("");
                                                    editTextPassword.setText("");


                                                    finish();
                                                } else {
                                                    Toast.makeText(MainSignup.this, task.getException().getMessage(),
                                                            Toast.LENGTH_SHORT).show();

                                                    Intent intent = new Intent(getApplicationContext(), MainSignup.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });


                                    }
                                }
                            });


                }}

     });}}
