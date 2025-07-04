package com.piisw.jpa.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.DiscriminatorColumn;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedEntityGraph(
    name = "Event.details", 
    attributeNodes = {
        @NamedAttributeNode("time"),
        @NamedAttributeNode("analysisRequired"),
        @NamedAttributeNode("comments"),
        @NamedAttributeNode("followers")
    }
)
public abstract class Event {

    @Id
    @SequenceGenerator(name = "EVENT_ID_GENERATOR", sequenceName = "EVENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_ID_GENERATOR")
    private Long id;
  

    @Column(nullable = false)
    private LocalDateTime time;

    private int duration;

    @Column(length = 10)
    private String threadId;

    @Column(length = 30)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVER_ID", nullable = false)
    private Server server;

    @Column
    private boolean analysisRequired;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Comment> comments;  

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Follower> followers;  


}
