package com.example.marc_hidalgo_dsa2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        }, 1000 * loadtime);

    }
}
