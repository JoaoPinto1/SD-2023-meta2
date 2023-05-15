package com.example.demo.WebSocket;

import com.example.demo.Downloader.Downloader;
import com.example.demo.RMIClient.Hello_C_I;
import com.example.demo.StorageBarrel.Storage_Barrels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramStatus {
    private List<Hello_C_I> downloaders;
    private List<Hello_C_I> barrels;
    private Map<String,String> topSearchs;

    public ProgramStatus() {
    }

    public ProgramStatus(List<Hello_C_I> downloaders, List<Hello_C_I> barrels, Map<String, String> topSearchs) {
        this.downloaders = downloaders;
        this.barrels = barrels;
        this.topSearchs = topSearchs;
    }

    public List<Hello_C_I> getDownloaders() {
        return downloaders;
    }

    public void setDownloaders(List<Hello_C_I> downloaders) {
        this.downloaders = downloaders;
    }

    public List<Hello_C_I> getBarrels() {
        return barrels;
    }

    public void setBarrels(List<Hello_C_I> barrels) {
        this.barrels = barrels;
    }

    public Map<String,String> getTopSearchs() {
        return topSearchs;
    }

    public void setTopSearches(Map<String,String> topSearchs) {
        this.topSearchs = topSearchs;
    }
}
