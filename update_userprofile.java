package com.example.headrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.headrest.databinding.ActivityUpdateUserprofileBinding;
import com.example.headrest.databinding.ActivityUpdateUserprofileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class update_userprofile extends AppCompatActivity {
    ActivityUpdateUserprofileBinding binding ;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FullName = binding.userName.getText().toString();
                String Email = binding.userEmail.getText().toString();
                String birthdate = binding.birthdate.getText().toString();

                updateData(FullName, Email, birthdate);
            }
        });
    }

    private void updateData(String fullName, String email, String birthdate) {

        HashMap User = new HashMap();
        User.put("Name", fullName);
        User.put("Email", email);
        User.put("Birthdate", birthdate);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.child(fullName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    binding.userName.setText("");
                    binding.userEmail.setText("");
                    binding.birthdate.setText("");

                    Toast.makeText(update_userprofile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();




                            Intent intent = new Intent(getApplicationContext(), hello_user.class);
                            startActivity(intent);
                            finish();

                } else {
                    Toast.makeText(update_userprofile.this, "Faild to Update", Toast.LENGTH_SHORT).show();
                }
            }});}}




