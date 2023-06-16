
package com.example.headrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;


public class hello_user extends AppCompatActivity {

    //--------------- for menu -------------------------------------------------
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    //---------------------------------------------------
    ImageView form ;
    ImageView results ;
    ImageView doctors;
    FirebaseAuth mAuth;

    ImageView logout2;

    TextView textView4 ;

    ProgressBar progressBar;

    //-------------------------------------------------------------------

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//-------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_user);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            //-----------------------------------------------------------------------
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item1: {
                        Toast.makeText(hello_user.this, "Home selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                        startActivity(intent);
                        finish();
                        break;
                    }

                    case R.id.item2: {
                        Toast.makeText(hello_user.this, "language selected", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.subitem1: {
                        findViewById(R.id.subitem1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                setLocale("ar");
                            }
                        });
                        break;
                    }

                    case R.id.subitem2: {
                        findViewById(R.id.subitem2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                setLocale("en");
                            }
                        });
                        break;
                    }

                    case R.id.item3: {
                        startActivity(new Intent(hello_user.this, contact_admin.class));
                        break;
                    }

                    case R.id.item4: {

                        AlertDialog.Builder builder = new AlertDialog.Builder(hello_user.this);
                        builder.setTitle("Logout");
                        builder.setMessage("Are you sure you want to logout? ");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAuth.signOut();
                                signOutUser();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.show();
                    }
                    break;
                }
                return true;
            }
        });

//-----------------------------------------------------------------------
        mAuth = FirebaseAuth.getInstance();
        form = findViewById(R.id.form);
        results = findViewById(R.id.results);
        doctors = findViewById(R.id.doctors);
        textView4 = findViewById(R.id.textView4);
        progressBar = findViewById(R.id.progressBar);


//------------------------------------------------------------------------

        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), form.class);
                startActivity(intent);
                finish();
            }
        });

        //----------------------------------------------------------------

        doctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), doctor_mainpage.class);
                startActivity(intent);
                finish();
            } });
        //----------------------------------------------------------------

        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), resultandchart.class);
                startActivity(intent);
                finish();
            } });
    }
    //----------------------------------------------------------------
    private void signOutUser() {
        Intent mainActivity = new Intent(hello_user.this, login.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
        startActivity(mainActivity);
        finish();
    }
    //---------------------- for change language -------------------------------------------------
    @SuppressWarnings("deprecation")
    public void setLocale(String lang){
        Locale myLocale = new Locale(lang);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf,dm);
        Intent refresh = new Intent(this, hello_user.class);
        startActivity(refresh);
    }
    //-------------------------- navigation menu ---------------------------------------------
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }
    //-----------------------------------------------------------------------
}