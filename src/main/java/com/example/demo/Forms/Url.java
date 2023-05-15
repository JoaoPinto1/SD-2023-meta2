package com.example.demo.Forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Url implements Serializable {
    private String url;
    public List<String> results;

    public Url() {

        url = "";
        results = new ArrayList<>();

    }

    public Url(String url , List<String> results) {

        this.url = url;
        this.results = results;
    }

    public String getUrl() {

        return this.url;

    }
    public void setUrl(String str) {
        url = str;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}