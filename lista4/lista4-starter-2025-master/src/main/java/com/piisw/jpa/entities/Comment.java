package com.piisw.jpa.entities;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    
    public String getContent() {
        return content;
    }

    public Event getEvent() {
        return event;
    }

    public void setContent(String content) {  
        this.content = content;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}
