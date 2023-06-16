package com.example.headrest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
public class form extends AppCompatActivity {

    private ProgressBar progressBar;
    private RadioButton radioButton1;
    private RadioGroup radioGroup1;
    private RadioButton radioButton2;
    private RadioGroup radioGroup2;
    private RadioButton radioButton3;
    private RadioGroup radioGroup3;
    private RadioButton radioButton4;
    private RadioGroup radioGroup4;
    private RadioButton radioButton5;
    private RadioGroup radioGroup5;
    private RadioButton Buttonexpore_lite;
    private RadioGroup Groupexpore_lite;





    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Form");

    Button button;
    Button back0;

    private ListView listView;

    @Override


    public void onStart() {
        super.onStart();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        button = findViewById(R.id.button3);

        progressBar = findViewById(R.id.progressBar2);



        radioGroup1= findViewById(R.id.user_location1);
        radioGroup1.clearCheck();


        radioGroup2= findViewById(R.id.pain_location);
        radioGroup2.clearCheck();


        radioGroup3= findViewById(R.id.state);
        radioGroup3.clearCheck();


        radioGroup4= findViewById(R.id.phone_useage_hour);
        radioGroup4.clearCheck();


        radioGroup5 = findViewById(R.id.activity);
        radioGroup5.clearCheck();

        Groupexpore_lite = findViewById(R.id.expore_lite);
        Groupexpore_lite.clearCheck();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);


                Intent intent = new Intent(getApplicationContext(), hello_user.class);
                startActivity(intent);
                finish();

                int loca = radioGroup1.getCheckedRadioButtonId();
                radioButton1 = findViewById(loca);

                int activity2 = radioGroup2.getCheckedRadioButtonId();
                radioButton2 = findViewById(activity2);

                int activity3 = radioGroup3.getCheckedRadioButtonId();
                radioButton3 = findViewById(activity3);

                int activity4 = radioGroup4.getCheckedRadioButtonId();
                radioButton4 = findViewById(activity4);

                int activity5 = radioGroup5.getCheckedRadioButtonId();
                radioButton5 = findViewById(activity5);

                int activity6 = Groupexpore_lite.getCheckedRadioButtonId();
                Buttonexpore_lite = findViewById(activity6);






                String loc, activity , state , pain , hours,expore_lite;

                //obtain the entered data
                loc = String.valueOf(radioButton1.getText());
                pain= String.valueOf(radioButton2.getText());
                state = String.valueOf(radioButton3.getText());
                hours  = String.valueOf(radioButton4.getText());
                activity  = String.valueOf(radioButton5.getText());
                expore_lite  = String.valueOf(Buttonexpore_lite.getText());




                // to save data on REALTIME DATABASE .
                HashMap<String, String > userMap = new HashMap<>();
                userMap.put("location" , loc);
                userMap.put("activity" , activity);
                userMap.put("state" , state);
                userMap.put("pain" , pain);
                userMap.put("hours" , hours);
                userMap.put("expore_lite" , expore_lite);


                //push generate a random key
                root.push().setValue(userMap);
            }
        });
        back0 = findViewById(R.id.back0);
        back0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(form.this, hello_user.class);
                startActivity(intent);
                finish();
            }
        });




    }}