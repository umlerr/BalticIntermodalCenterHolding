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
@Table(name = "Arrival")
public class Arrival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date russiaDeliveryDate;

    private String stock;

    private String damageRepair;

    private Date checkpointDate;

    private String direction;

    private Integer usedDays;

    @OneToOne
    @JoinColumn(name = "CIRCLE_ID")
    private Circle circle;
}
