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
    private Integer Id;

    private Date Russia_Delivery_Date;

    private String Stock;

    private String Damage_Repair;

    private Date Checkpoint_Date;

    private String Direction;

    private Integer Used_Days;

    @OneToOne
    @JoinColumn(name = "CIRCLE_ID")
    private Circle circle;
}
