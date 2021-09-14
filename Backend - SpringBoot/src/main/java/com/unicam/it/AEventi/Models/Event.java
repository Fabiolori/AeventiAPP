package com.unicam.it.AEventi.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;



@Entity
@Table(name = "events")
    public class Event {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "time")
    private Date time;
    @ManyToOne
    private Account publisher;
    @Column(name = "type")
    private String type;

    public Event (){}

    public Event (String name, String description, String type, Date time, Account publisher ) {
            this.setName(name);
            this.setDescription(description);
            this.setType(type);
            this.setTime(time);
            this.setPublisher(publisher);

    }

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getPublisher() {
        return publisher;
    }

    public void setPublisher(Account publisher) {
        this.publisher = publisher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", time=" + time +
                ", publisher=" + publisher +
                '}';
    }
}
