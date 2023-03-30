package com.iut.app.android.tp5_reseau_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent iHome = new Intent(HomeActivity.this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(iHome);
            }
        }, 4000);


    }
}