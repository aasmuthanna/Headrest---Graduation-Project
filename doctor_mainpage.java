package com.example.headrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Request;




public class doctor_mainpage extends AppCompatActivity {
    TextView send1,send2,delete1,delete2,contact1;
    ImageView contact2;

    // String userEmail,doctorEmail;
    DatabaseReference requestsRef = FirebaseDatabase.getInstance().getReference("requests");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_mainpage);
        send1=findViewById(R.id.SendReport);

        send2=findViewById(R.id.SendReport2);
        contact1=findViewById(R.id.chattext);



        DatabaseReference requestsRef = FirebaseDatabase.getInstance().getReference("requests");
        // Assuming you have initialized FirebaseApp and DatabaseReference

        send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Request added successfully
                Toast.makeText(getApplicationContext(), "Your report send seccussfully", Toast.LENGTH_SHORT).show();
            }
        });

        send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Request added successfully
                Toast.makeText(getApplicationContext(), "Your report send seccussfully", Toast.LENGTH_SHORT).show();
            }
        });

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), send_email.class);
                startActivity(intent);
                finish();
            }
        });

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), send_email.class);
                startActivity(intent);
                finish();
            }
        });







    }
}