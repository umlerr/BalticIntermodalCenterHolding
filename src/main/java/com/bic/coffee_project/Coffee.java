package com.bic.coffee_project;

import jakarta.persistence.*;


@Entity
@Table(name = "COFFEE")
class Coffee {
    @Column(name = "name")
    private String Name;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;
    public Coffee() {

    }
    public String getName() {
        return Name;
    }
    public Coffee(String name) {
        this.Name = name;
    }
    public Integer getId() {
        return Id;
    }

}
