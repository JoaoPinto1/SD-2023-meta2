package com.example.demo.WebSocket;

import com.example.demo.Downloader.Downloader;
import com.example.demo.StorageBarrel.Storage_Barrels;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.List;

@Component
public class ProgramStatusUpdater{

    private final SimpMessagingTemplate messagingTemplate;

    public ProgramStatusUpdater(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {


        ProgramStatus status = getProgramStatus();

        // Send the server status to the clients subscribed to the '/topic/serverStatus' destination
        messagingTemplate.convertAndSend("/topic/serverStatus", status);

    }


    public ProgramStatus getProgramStatus(){
        ProgramStatus serverStatus = new ProgramStatus();
        serverStatus.setDownloaders(getActiveDownloaders());
        serverStatus.setBarrels(getActiveBarrels());
        serverStatus.setTopSearches(getTopSearches());

        return serverStatus;
    }



    private List<Downloader> getActiveDownloaders() {
        // Logic to fetch active downloaders
        return null;// Retrieve the list of active downloaders
    }

    private List<Storage_Barrels> getActiveBarrels() {
        // Logic to fetch active barrels
        return null;// Retrieve the list of active barrels
    }

    private HashMap<String,String> getTopSearches() {
        // Logic to fetch top searches
        return null;// Retrieve the list of top searches
    }

}
