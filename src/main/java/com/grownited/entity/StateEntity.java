package com.grownited.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@jakarta.persistence.Entity
@Data  // Lombok annotation to generate getters, setters, toString, equals, hashcode
@Table(name = "state")
public class StateEntity {

    @Id
    @GeneratedValue
    private Integer stateId;
    private String stateName;
}
