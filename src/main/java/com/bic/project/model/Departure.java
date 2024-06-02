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
    private Integer Id;

    private Date Date;

    private Date Delivery_Date;

    private String China_Location;

    private Date Pickup_Date;

    private String Delivery_Location;

    private String Annex;

    private Integer Compensation;

    @OneToOne
    @JoinColumn(name = "CIRCLE_ID")
    private Circle circle;
}
