package com.piisw.jpa.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false)
    private LocalDateTime subscriptionDate;

    // Gettery i settery
    public String getUserId() {
        return userId;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setUserId(String userId) { 
        this.userId = userId;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {  
        this.subscriptionDate = subscriptionDate;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
