package com.bic.project.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTAINER")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String Number;

    private Integer Price;

    private String Type;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Circle> circleList = new ArrayList<>();

    public Integer getMaxCircle() {
        Integer max = 0, currNumber;
        for (Circle circle : this.circleList) {
            currNumber = circle.getNumber();
            max = currNumber > max ? currNumber : max;
        }
        return max;
    }

    public Circle getCircleByNumber(Integer currNumber) {
        Circle currCircle = null;
        Integer number;
        for (Circle circle : this.circleList) {
            number = circle.getNumber();
            currCircle = Objects.equals(currNumber, number) ? circle : null;
        }
        return currCircle;
    }

    public String getCurrentLocation() {
        Integer currCircle = getMaxCircle();
        Circle circle = getCircleByNumber(currCircle);
        if (circle == null) {
            return null;
        }
        Arrival arrival = circle.getArrival();
        Departure departure = circle.getDeparture();
        if (arrival != null) {
            if (arrival.getCheckpoint_Date() == null) {
                return arrival.getRussia_Delivery_Date() == null ? null : arrival.getStock();
            } else {
                return "КП + Направление: " + arrival.getDirection();
            }
        } else {
            if (departure.getPickup_Date() == null) {
                return departure.getDelivery_Date() == null ? null : departure.getChina_Location();
            } else {
                return departure.getDelivery_Location();
            }
        }
    }

    public Date getCurrentDate() {
        Integer currCircle = getMaxCircle();
        Circle circle = getCircleByNumber(currCircle);
        if (circle == null) {
            return null;
        }
        Arrival arrival = circle.getArrival();
        Departure departure = circle.getDeparture();
        if (arrival != null) {
            if (arrival.getCheckpoint_Date() == null) {
                return arrival.getRussia_Delivery_Date() == null ? null : arrival.getRussia_Delivery_Date();
            } else {
                return arrival.getCheckpoint_Date();
            }
        } else {
            if (departure.getPickup_Date() == null) {
                return departure.getDelivery_Date() == null ? null : departure.getDelivery_Date();
            } else {
                return departure.getPickup_Date();
            }
        }
    }
}

