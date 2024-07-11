package com.bic.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Departure")
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date deliveryDate;

    private String chinaLocation;

    private Date pickupDate;

    private String deliveryLocation;

    private String annex;

    private Integer compensation;

    @OneToOne
    @JoinColumn(name = "CIRCLE_ID")
    private Circle circle;
}
