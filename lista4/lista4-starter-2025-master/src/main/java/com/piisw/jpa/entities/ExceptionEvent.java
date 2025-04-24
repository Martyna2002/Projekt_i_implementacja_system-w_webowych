package com.piisw.jpa.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@DiscriminatorValue("EXCEPTION")
public class ExceptionEvent extends Event {

    private String exceptionName;

    private String occuranceClass;

    private String occuranceMethod;

    private Integer occuranceLine;

}
