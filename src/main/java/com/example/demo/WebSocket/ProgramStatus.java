package com.example.demo.WebSocket;

import com.example.demo.Downloader.Downloader;
import com.example.demo.StorageBarrel.Storage_Barrels;

import java.util.List;

public class ProgramStatus {
    private List<Downloader> downloaders;
    private List<Storage_Barrels> barrels;
    private List<String> topSearchs;

    public ProgramStatus() {
    }

    public List<Downloader> getDownloaders() {
        return downloaders;
    }

    public void setDownloaders(List<Downloader> downloaders) {
        this.downloaders = downloaders;
    }

    public List<Storage_Barrels> getBarrels() {
        return barrels;
    }

    public void setBarrels(List<Storage_Barrels> barrels) {
        this.barrels = barrels;
    }

    public List<String> getTopSearchs() {
        return topSearchs;
    }

    public void setTopSearches(List<String> topSearchs) {
        this.topSearchs = topSearchs;
    }
}
