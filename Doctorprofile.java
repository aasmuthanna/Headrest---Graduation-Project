package com.example.headrest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Doctorprofile extends AppCompatActivity {
    private TextView profileName, profileEmail, profileBirthdate;

    private android.widget.Button Button;
    private DatabaseReference usersRef;
    private FirebaseUser currentUser;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorprofile);

        // Initialize views
        profileName = findViewById(R.id.doctorName);
        profileEmail = findViewById(R.id.doctorEmail);
        profileBirthdate = findViewById(R.id.birthdate);
        Button=findViewById(R.id.updateBtn);

        // Get references to Firebase instances
        usersRef = FirebaseDatabase.getInstance().getReference().child("Doctors");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            userEmail = currentUser.getEmail();
            retrieveProfileData();
        }

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), update_doctorprofile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void retrieveProfileData() {
        Query query = usersRef.orderByChild("Email").equalTo(userEmail);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();

                    String name = userSnapshot.child("Name").getValue(String.class);
                    String email = userSnapshot.child("Email").getValue(String.class);
                    String birthdate = userSnapshot.child("Birthdate").getValue(String.class);


                    // Update the UI
                    profileName.setText(name);
                    profileEmail.setText(email);
                    profileBirthdate.setText(birthdate);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error retrieving data from Firebase: " + databaseError.getMessage());
            }
        });
    }

}
