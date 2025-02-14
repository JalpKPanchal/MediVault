package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    private String cityName;

    @ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
    private StateEntity state;
}
