package com.example.marc_hidalgo_dsa2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Random;

public class Splash extends AppCompatActivity {
    private ProgressBar splashProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //BASE_URL = getString(R.string.URL_BASE);

        splashProgress = findViewById(R.id.splashProgressBar);
        Random r = new Random();
        int loadtime = (r.nextInt(5 - 4) + 4);


        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Open the ListActivity
                Intent intent = new Intent(getApplicationContext(), BuscarUser.class);
                startActivity(intent);
                finish();
            }
        }, 1000 * loadtime);

    }
}
