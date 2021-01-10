package com.example.marc_hidalgo_dsa2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
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

public class Login extends AppCompatActivity {
    private EditText nameUser;
    private EditText passwordUser;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    Retrofit retrofit;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private String BASE_URL;
    private ProgressDialog progressDialog;


    //para la interfaz
    APIservice userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameUser = (EditText) findViewById(R.id.editUsername);
        passwordUser = (EditText) findViewById(R.id.editPassword);

        BASE_URL="http://147.83.7.203:8080/myapp/";

        preferences =  Login.this.getPreferences(Context.MODE_PRIVATE);

        String user = preferences.getString("user","incorrect");
        String password = preferences.getString("dsamola","incorrect");

        if(user.equals("user")&&password.equals("dsamola"))
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        //Esto es configuracion del recycler
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Configuracion del retrofit
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Attaching Interceptor to a client
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                //               .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .baseUrl("http://147.83.7.204:8080/dsaApp/")
                // .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        userService = retrofit.create(APIservice.class);
    }
    /*
    //Notifica mensajes
    private void NotifyUser(String MSG){
        Toast toast = Toast.makeText(Login.this,MSG,Toast.LENGTH_SHORT);
        toast.show();
    }*/
    public void IniciarSesion() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando Sesión");
        progressDialog.show();

        APIservice apiService = retrofit.create(APIservice.class);

        //JSON que enviamos al servido
        final UsuarioJSON usuario = new UsuarioJSON(nameUser.getText().toString(), passwordUser.getText().toString());

        Call<KeyUser> login = apiService.login2(usuario);
        login.enqueue(new Callback<KeyUser>() {
            @Override
            public void onResponse(Call<KeyUser> login, Response<KeyUser> response) {
                progressDialog.dismiss();
                int key = response.body().getKey();
                if (key == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                //El usuario está autentificado
                usuario.setKey(key);
                Toast toast = Toast.makeText(getApplicationContext(), "Bienvenido  " + usuario.toString(), Toast.LENGTH_SHORT);
                toast.show();

                SharedPreferences sharedpref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpref.edit();
                editor.putString("username", nameUser.getText().toString());
                editor.putString("password", passwordUser.getText().toString());
                editor.putInt("key", key);
                editor.apply();

                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("usuario", usuario);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<KeyUser> login, Throwable t) {
                progressDialog.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
    public void onLogbuttonClick (View view){
        if (TextUtils.isEmpty(nameUser.getText().toString()) || TextUtils.isEmpty(passwordUser.getText().toString()))
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Campos incompletos", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        IniciarSesion();
    }


}