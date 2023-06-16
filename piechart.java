package com.example.headrest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class piechart extends AppCompatActivity {
    private static final String TAG = "piechart";
    private PieChart activityPieChart;
    private PieChart locationPieChart;
    private PieChart painPieChart;
    private PieChart hoursPieChart;
    private PieChart statePieChart;
    private PieChart lightPieChart;
    ImageView back;

    private DatabaseReference usersRef;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        activityPieChart = findViewById(R.id.activityPieChart);
        locationPieChart = findViewById(R.id.locationPieChart);
        painPieChart = findViewById(R.id.painPieChart);
        hoursPieChart = findViewById(R.id.hoursPieChart);
        statePieChart = findViewById(R.id.statePieChart);
        lightPieChart = findViewById(R.id.lightPieChart);

        back = findViewById(R.id.goback);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), resultandchart.class);
                startActivity(intent);
                finish();
            }
        });





        usersRef = FirebaseDatabase.getInstance().getReference("Form");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = currentUser.getEmail();

// Create a query to retrieve data for the current user by email
        Query query = FirebaseDatabase.getInstance().getReference("Form")
                .orderByChild("Email")
                .equalTo(userEmail);

        Query query2 = FirebaseDatabase.getInstance().getReference()
                .orderByChild("Email")
                .equalTo(userEmail);



        // Activity Type **************************************************************
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int countWork = 0;
                int counthome= 0;
                int countschool = 0;
                int countout = 0;
                int countother=0;

                for (DataSnapshot formSnapshot : dataSnapshot.getChildren()) {
                    String activityType = formSnapshot.child("location").getValue(String.class);

                    if (activityType != null) {
                        switch (activityType) {
                            case "Work":
                                countWork++;
                                break;
                            case "home":
                                counthome++;
                                break;
                            case "school":
                                countout++;
                                break;
                            case "out":
                                countother++;
                                break;
                            case "other":
                                countother++;
                                break;


                        }
                    }
                }

                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(countWork));
                entries.add(new PieEntry(counthome));
                entries.add(new PieEntry(countschool));
                entries.add(new PieEntry(countout));
                entries.add(new PieEntry(countother));

                PieDataSet dataSet = new PieDataSet(entries, "location Type");
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                PieData pieData = new PieData(dataSet);

                locationPieChart.setData(pieData);
                locationPieChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError2) {
                Log.e(TAG, "Error retrieving data from Firebase", databaseError2.toException());
            }
        });


        // Pain ******************************************************************************************
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot2) {
                int leftCount = 0;
                int rightCount = 0;
                int frontCount = 0;
                int backCount = 0;
                int otherCount = 0;

                for (DataSnapshot formSnapshot2 : dataSnapshot2.getChildren()) {
                    String pain = formSnapshot2.child("pain").getValue(String.class);

                    if (pain != null) {
                        switch (pain) {
                            case "left":
                                leftCount++;
                                break;
                            case "right":
                                rightCount++;
                                break;
                            case "front":
                                frontCount++;
                                break;
                            case "back":
                                backCount++;
                                break;
                            case "other":
                                otherCount++;
                                break;
                        }
                    }
                }

                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(leftCount));
                entries.add(new PieEntry(rightCount));
                entries.add(new PieEntry(frontCount));
                entries.add(new PieEntry(backCount));
                entries.add(new PieEntry(otherCount));

                PieDataSet dataSet2 = new PieDataSet(entries, "Pain Type");
                dataSet2.setColors(ColorTemplate.MATERIAL_COLORS);

                PieData pieData2 = new PieData(dataSet2);
                painPieChart.setData(pieData2);
                painPieChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error retrieving data from Firebase", databaseError.toException());
            }
        });

        // Hours **************************************************************
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot3 ){
                int count_1_3 = 0;
                int count_4_6 = 0;
                int count_6_8 = 0;
                int count_8 = 0;

                for (DataSnapshot formSnapshot3 : dataSnapshot3.getChildren()) {
                    String hours = formSnapshot3.child("hours").getValue(String.class);

                    if (hours != null) {
                        switch (hours) {
                            case "_1_3":
                                count_1_3++;
                                break;
                            case "_4_6":
                                count_4_6++;
                                break;
                            case "_6_8 ":
                                count_6_8++;
                                break;
                            case "_8":
                                count_8++;
                                break;
                        }
                    }
                }

                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(count_1_3));
                entries.add(new PieEntry(count_4_6));
                entries.add(new PieEntry(count_6_8));
                entries.add(new PieEntry(count_8));

                PieDataSet dataSet3 = new PieDataSet(entries, "Hours Usage Phone");
                dataSet3.setColors(ColorTemplate.MATERIAL_COLORS);

                PieData pieData4 = new PieData(dataSet3);
                hoursPieChart.setData(pieData4);
                hoursPieChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error retrieving data from Firebase", databaseError.toException());
            }
        });

        // Activity *********************************************************************************************
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot4){
                int count_1_3 = 0;
                int count_4_6 = 0;
                int count_6_8 = 0;
                int count_8 = 0;

                for (DataSnapshot formSnapshot4 : dataSnapshot4.getChildren()) {
                    String hours = formSnapshot4.child("activity").getValue(String.class);

                    if (hours != null) {
                        switch (hours) {
                            case "high_activity":
                                count_1_3++;
                                break;
                            case "low_activity":
                                count_4_6++;
                                break;
                            case "no_activity":
                                count_6_8++;
                                break;

                        }
                    }
                }

                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(count_1_3));
                entries.add(new PieEntry(count_4_6));
                entries.add(new PieEntry(count_6_8));


                PieDataSet dataSet4 = new PieDataSet(entries, " Activity");
                dataSet4.setColors(ColorTemplate.MATERIAL_COLORS);

                PieData pieData4 = new PieData(dataSet4);
                activityPieChart.setData(pieData4);
                activityPieChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error retrieving data from Firebase", databaseError.toException());
            }
        });



        // Psychology State ****************************************************************************
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot5) {
                int countHappy = 0;
                int countSad = 0;
                int countNormal = 0;
                int countAngry = 0;

                for (DataSnapshot formSnapshot5 : dataSnapshot5.getChildren()) {
                    String state = formSnapshot5.child("state").getValue(String.class);

                    if (state != null) {
                        switch (state) {
                            case "happy":
                                countHappy++;
                                break;
                            case "sad":
                                countSad++;
                                break;
                            case "normal":
                                countNormal++;
                                break;
                            case "angry":
                                countAngry++;
                                break;
                        }
                    }
                }

                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(countHappy));
                entries.add(new PieEntry(countSad));
                entries.add(new PieEntry(countNormal));
                entries.add(new PieEntry(countAngry));

                PieDataSet dataSet5 = new PieDataSet(entries, "Psychological State");
                dataSet5.setColors(ColorTemplate.MATERIAL_COLORS);

                PieData pieData5 = new PieData(dataSet5);
                statePieChart.setData(pieData5);
                statePieChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError5) {
                Log.e(TAG, "Error retrieving data from Firebase", databaseError5.toException());
            }
        });

        // Exposer Light *******************************************************************************
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot6) {
                int low = 0;
                int middle = 0;
                int strong = 0;
                int veryStrong = 0;

                for (DataSnapshot formSnapshot6 : dataSnapshot6.getChildren()) {
                    String lightExposure = formSnapshot6.child("expore_lite").getValue(String.class);

                    if (lightExposure != null) {
                        switch (lightExposure) {
                            case "low":
                                low++;
                                break;
                            case "middle":
                                middle++;
                                break;
                            case "strong":
                                strong++;
                                break;
                            case "very_strong":
                                veryStrong++;
                                break;
                        }
                    }
                }

                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(low));
                entries.add(new PieEntry(middle));
                entries.add(new PieEntry(strong));
                entries.add(new PieEntry(veryStrong));

                PieDataSet dataSet6 = new PieDataSet(entries, "Exposer Light");
                dataSet6.setColors(ColorTemplate.MATERIAL_COLORS);

                PieData pieData6 = new PieData(dataSet6);
                lightPieChart.setData(pieData6);
                lightPieChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError6) {
                Log.e(TAG, "Error retrieving data from Firebase", databaseError6.toException());
            }
        });

    }
}
