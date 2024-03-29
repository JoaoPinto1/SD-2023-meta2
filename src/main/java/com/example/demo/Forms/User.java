package com.example.demo.Forms;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;

    public User() {}

    public User(String username , String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username){
        this.username = username;
    }

    @Override
    public String toString() {
        return "user{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}