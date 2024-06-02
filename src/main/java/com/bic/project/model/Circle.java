package com.bic.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CIRCLE")
public class Circle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Integer Number;

    @ManyToOne
    @JoinColumn(name = "CONTAINER_ID")
    private Container container;

    @OneToOne(mappedBy = "circle", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Arrival arrival;

    @OneToOne(mappedBy = "circle", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Departure departure;
}
