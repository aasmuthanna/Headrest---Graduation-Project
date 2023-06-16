package com.example.headrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;

public class doctors_login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth ;
    ProgressBar progressBar;
    TextView textView;
    TextView textView2 ;

    RadioButton user ;
    RadioButton doctor ;

    Button login;



    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Users");

    Button ar;
    Button en;







    @Override

    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.text_for_forget_pass);






        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), doctors_signup.class);
                startActivity(intent);
                finish();
            }
        });



        //Reset Password
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(doctors_login.this, "You Can Reset Your Password Now!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(doctors_login.this, forgetpassword.class));
            }
        });





        buttonLogin.setOnClickListener(new View.OnClickListener() {


            @Override


            public void onClick(View v) {

                String Email, Password ;


                Email = String.valueOf(editTextEmail.getText());
                Password = String.valueOf(editTextPassword.getText());



                if (Email.equals("") && Password.equals("") ){

                    Toast.makeText(doctors_login.this, "All Fields are required", Toast.LENGTH_SHORT).show();
                    editTextEmail.setError("Email is required");
                    editTextPassword.setError("Password is required");
                }
                else if (Email.equals("")){

                    editTextEmail.setError("Email is required");
                }
                else if (Password.equals("")){

                    editTextPassword.setError("Password is required");
                }


                else{









                    mAuth.signInWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        Toast.makeText(doctors_login.this, "Login Successful",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), doctor.class);
                                        startActivity(intent);
                                        finish();





                                    } else {

                                        Toast.makeText(doctors_login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }} });}}