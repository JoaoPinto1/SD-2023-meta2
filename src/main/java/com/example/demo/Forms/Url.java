package com.example.demo.Forms;

import java.io.Serializable;

public class Url implements Serializable {
    private String url;
    private String[] results;

    public Url() {}

    public Url(String url , String[] results) {

        this.url = url;
        this.results = results;
    }

    public String getUrl() {

        return this.url;

    }
    public void setUrl(String url) {
        this.url = url;
    }

    public void setResults(String[] results) {
        this.results = results;
    }

    public String[] getResults(){
        return this.results;
    }
}