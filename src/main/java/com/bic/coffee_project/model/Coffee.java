package com.bic.coffee_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COFFEE")
public class Coffee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;

    private String Name;
}
