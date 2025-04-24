package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Server;

public class ServerStatistic {

    private Server server; 
    private Long count;

   
    public ServerStatistic(Server server, Long count) {
        this.server = server;
        this.count = count;
    }

   
    public Server getServer() {
        return server;
    }

  
    public Long getCount() {
        return count;
    }
}
