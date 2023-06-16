package com.example.headrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Users extends AppCompatActivity {

    private RecyclerView messagesRecyclerView;
    private String Number ;
    private  String Email ;
    private String FullName ;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://headrest-d45ae-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        final CircleImageView userProfilePic = findViewById(R.id.userProfilePic);

        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);

        //get instance data from Signup.class activity
        Email = getIntent().getStringExtra("Email");
        Number = getIntent().getStringExtra("Phone Number");
        FullName = getIntent().getStringExtra("Full Name");

        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();






        //get profile pic from firebase database

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                final String profilePicUrl = snapshot.child("users").child(Number).child("Profile_Pic").getValue(String.class);



                if(!profilePicUrl.isEmpty()){

                    //set profile pic to circle image view
                    Picasso.get().load(profilePicUrl).into(userProfilePic);


            }

                progressDialog.dismiss(); }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();

            }
        });
    }
}