package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private boolean completed = false;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    public Item() {
    }

    public Item(Long id, String name, boolean completed, Date date) {
        this.id = id;
        this.name = name;
        this.completed = completed;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
