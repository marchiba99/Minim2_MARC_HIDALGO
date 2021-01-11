package com.example.marc_hidalgo_dsa2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIservice
{
    @GET("users/{Username}")
    Call<Usuario> userInfo (@Path("Username") String username);

    @GET("users/{username}/followers")
    Call<List<Repositorios>> Repositorios(@Path("username") String username);


}