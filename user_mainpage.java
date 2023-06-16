package com.example.headrest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class user_mainpage extends AppCompatActivity {

    TextView SendReport;
    TextView contact ;

    ImageView back ;
    TextView report ;
    TextView contact2;
    TextView report2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mainpage);
        back=findViewById(R.id.back);
        report=findViewById(R.id.SendReport);
        contact = findViewById(R.id.contact1);
        contact2 = findViewById(R.id.contact2);
        report2 = findViewById(R.id.SendReport2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), doctor.class);
                startActivity(intent);
                finish();
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), fati_result_and_chart.class);
                startActivity(intent);
                finish();
            }
        });



        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), send_email.class);
                startActivity(intent);
                finish();
            }
        });
        report2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), huda_easult_and_chart.class);
                startActivity(intent);
                finish();
            }
        });




    }}