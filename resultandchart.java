package com.example.headrest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class resultandchart extends AppCompatActivity {
    Button bar ,pie,res;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultandchart);
        bar=findViewById(R.id.barchart);
        pie=findViewById(R.id.piechart);
        res=findViewById(R.id.result);
        back=findViewById(R.id.goback);

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), barchart.class);
                startActivity(intent);
                finish();
            }
        });
        pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),piechart.class);
                startActivity(intent);
                finish();
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),result.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),hello_user.class);
                startActivity(intent);
                finish();
            }
        });



    }
}