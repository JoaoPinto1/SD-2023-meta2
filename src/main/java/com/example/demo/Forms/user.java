package com.example.demo.Forms;

import java.io.Serializable;

public class user implements Serializable {
    private String username;
    private String password;

    public user() {}

    public user(String username , String password) {
        this.username = username;
        this.password = password;
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

}