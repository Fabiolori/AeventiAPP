package com.unicam.it.AEventi.Models;

import javax.persistence.*;

@Entity
@Table(name = "eventtype")
public class EventType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    @Column(name = "name")
    private String name;

    public EventType (){}

    public EventType(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
