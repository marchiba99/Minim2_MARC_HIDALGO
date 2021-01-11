package com.example.marc_hidalgo_dsa2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscarUser extends AppCompatActivity {
    private EditText nameUser;

    /*Retrofit retrofit;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private String BASE_URL;*/
    private ProgressDialog progressDialog;


    //para la interfaz
    //APIservice userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameUser = (EditText) findViewById(R.id.editUsername);

        //BASE_URL="https://api.github.com/";


        //Esto es configuracion del recycler
        /*recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Configuracion del retrofit
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Attaching Interceptor to a client
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        userService = retrofit.create(APIservice.class);*/
    }

    public void Buscar() {
        /*if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build();
        }*/

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Buscando");
        progressDialog.show();

        //APIservice apiService = retrofit.create(APIservice.class);

        //JSON que enviamos al servidor
        //final Usuario usuario = new Usuario(nameUser.getText().toString());

        /*Call<Usuario> userInfo = apiService.userInfo(nameUser.getText().toString());
        userInfo.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> userInfo, Response<Usuario> response) {
                progressDialog.dismiss();

                Toast toast = Toast.makeText(getApplicationContext(), "Buscando a usuario: " + usuario.toString(), Toast.LENGTH_SHORT);*/

                //editor.putString("username", nameUser.getText().toString());
                Intent intent = new Intent(BuscarUser.this, MainActivity.class);
                intent.putExtra("usuario", nameUser.toString());
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }


           /* public void onFailure(Call<Usuario> userInfo, Throwable t) {
                progressDialog.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });*/
    public void onLogbuttonClick (View view){
        if (TextUtils.isEmpty(nameUser.getText().toString()))
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Campos incompletos", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        Buscar();
    }


}