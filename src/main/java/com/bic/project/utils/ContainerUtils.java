package com.bic.project.utils;

import com.bic.project.model.Arrival;
import com.bic.project.model.Circle;
import com.bic.project.model.Departure;
import com.bic.project.model.Container;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class ContainerUtils {

    public static Integer getMaxCircle(Container container) {
        Integer max = 0, currNumber;
        for (Circle circle : container.getCircleList()) {
            currNumber = circle.getNumber();
            max = currNumber > max ? currNumber : max;
        }
        return max;
    }

    public static Circle getCircleByNumber(Container container, Integer currNumber) {
        for (Circle circle : container.getCircleList()) {
            if (Objects.equals(currNumber, circle.getNumber())) {
                return circle;
            }
        }
        return null;
    }

    public static String getCurrentLocation(Container container) {
        Integer currCircle = getMaxCircle(container);
        Circle circle = getCircleByNumber(container, currCircle);
        if (circle == null) {
            return null;
        }
        Arrival arrival = circle.getArrival();
        Departure departure = circle.getDeparture();
        if (arrival != null) {
            if (arrival.getCheckpointDate() == null) {
                return arrival.getRussiaDeliveryDate() == null ? null : arrival.getStock();
            } else {
                return "КП + Направление: " + arrival.getDirection();
            }
        } else {
            if (departure.getPickupDate() == null) {
                return departure.getDeliveryDate() == null ? null : departure.getChinaLocation();
            } else {
                return departure.getDeliveryLocation();
            }
        }
    }

    public static Date getCurrentDate(Container container) {
        Integer currCircle = getMaxCircle(container);
        Circle circle = getCircleByNumber(container, currCircle);
        if (circle == null) {
            return null;
        }
        Arrival arrival = circle.getArrival();
        Departure departure = circle.getDeparture();
        if (arrival != null) {
            if (arrival.getCheckpointDate() == null) {
                return arrival.getRussiaDeliveryDate() == null ? null : arrival.getRussiaDeliveryDate();
            } else {
                return arrival.getCheckpointDate();
            }
        } else {
            if (departure.getPickupDate() == null) {
                return departure.getDeliveryDate() == null ? null : departure.getDeliveryDate();
            } else {
                return departure.getPickupDate();
            }
        }
    }
}
