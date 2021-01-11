package com.example.marc_hidalgo_dsa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
   // List<Repositorios> ListaRepos = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter RecyclerAdapter;
    APIservice userService;
    public static Context context;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar progressBar;

    TextView repositorio;
    TextView seguidores;
    ImageView imagen;

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build();
    APIservice apiService = retrofit.create(APIservice.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        progressBar = findViewById(R.id.progressBar);

        repositorio = findViewById(R.id.repositorios);
        seguidores = findViewById(R.id.followers);

        //Configuracion del retrofit
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        userService = retrofit.create(APIservice.class);
        String Nusuario;
        String b;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                b = null;
            } else {
                b = extras.getString("usuario");
            }
        } else {
            b = (String) savedInstanceState.getSerializable("usuario");
        }
        Nusuario=b;

        Call<Usuario> userInfo = apiService.userInfo(Nusuario);

        userInfo.enqueue(new Callback <Usuario>() {

            @Override
            public void onResponse(Call<Usuario> userInfo, Response<Usuario> response) {
                if (!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "No existe el usuario", Toast.LENGTH_SHORT);
                    Usuario user = response.body();
                    repositorio.setText(Integer.toString(user.getPublic_repos()));
                    seguidores.setText(Integer.toString(user.getFollowers()));
                }
            }


            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ha habido un error", Toast.LENGTH_SHORT);

            }
        });
        context = getApplicationContext();
        imagen = findViewById(R.id.imageView);
        Glide.with(context).load(imagen).into(imagen);

    }
}
