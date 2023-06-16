package com.example.headrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    private Button buttonForReset ;
    private EditText Email2  ;
    private ProgressBar progressBar;
    private FirebaseAuth authProfile ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        getSupportActionBar().setTitle("Forgot Password");

        Email2 = findViewById(R.id.email);
        buttonForReset = findViewById(R.id.button_for_reset_pass);
        progressBar = findViewById(R.id.progressBar);

        buttonForReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email2.getText().toString();

                if (TextUtils.isEmpty(email)){

                    Toast.makeText(forgetpassword.this, "Please Enter Your Registered Email", Toast.LENGTH_SHORT).show();
                    Email2.setError("Email is Required");
                    Email2.requestFocus();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    Toast.makeText(forgetpassword.this, "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                    Email2.setError("Valid Email is Required");
                    Email2.requestFocus();

                }

                else {
                  progressBar.setVisibility(View.VISIBLE);
                  resetPassword(email);


                }
            }
        });
    }

    private void resetPassword( String email ) {
        authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(forgetpassword.this, "Please check your inbox for password reset link",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(forgetpassword.this, MainActivity.class);


                    // Clear stack to prevent user coming back to forgetpassword activity.
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }

                else {

                    Toast.makeText(forgetpassword.this, "Something Went Wrong",
                            Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}