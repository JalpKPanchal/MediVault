package com.grownited.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "clinic")
public class ClinicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clinicId;

    @Column(nullable = false)
    private String clinicName;

    @Column(nullable = false)
    private String timing;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer phoneNo;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal rating;

    private String about;

    private BigDecimal lat;

    private BigDecimal log;

    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false)
    private CityEntity city;

    @ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
    private StateEntity state;

    private Integer pincode;
}
