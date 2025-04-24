package com.piisw.jpa.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class Server {

    @Id
    @SequenceGenerator(name = "SERVER_ID_GENERATOR", sequenceName = "SERVER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVER_ID_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ip;

   
    @Version
    private int version;


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

  
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

   
    @Column(nullable = false)
    private boolean isActive = true;

    public Server(String name, String ip) {
        this.name = name;
        this.ip = ip;
        this.isActive = true;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
