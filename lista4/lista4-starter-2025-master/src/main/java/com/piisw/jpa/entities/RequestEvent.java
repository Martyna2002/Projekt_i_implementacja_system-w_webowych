package com.piisw.jpa.entities;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter

public class RequestEvent extends Event {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HttpMethod method;



}
