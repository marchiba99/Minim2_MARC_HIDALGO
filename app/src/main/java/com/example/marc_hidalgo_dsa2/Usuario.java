package com.example.marc_hidalgo_dsa2;


import java.io.Serializable;

public class Usuario implements Serializable {

    private String name;
    private int followers;
    private int public_repos;

    public Usuario(String name) {
        this.name = name;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", nombre='" + name + '\'' +
                '}';
    }

}
