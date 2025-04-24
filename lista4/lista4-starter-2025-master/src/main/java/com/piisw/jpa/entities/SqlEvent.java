package com.piisw.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Setter

public class SqlEvent extends Event {

    @Column(length = 4000, nullable = false)
    private String sqlQuery;

}
