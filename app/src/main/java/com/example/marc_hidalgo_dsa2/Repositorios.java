package com.example.marc_hidalgo_dsa2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repositorios implements Serializable {
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("machinename")
    @Expose
    private String machinename;
}
