package com.learning.models;

import javax.persistence.*;

@Entity
public class Gradations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_rec;
    private String name;
    private String description;
//    @OneToOne(mappedBy = "gradationItem")
//    private Timers timers;

    public Integer getId_rec() {
        return id_rec;
    }

    public void setId_rec(Integer id_rec) {
        this.id_rec = id_rec;
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

    public void setDescription(String description) {
        this.description = description;
    }

//    public Timers getTimers() {
//        return timers;
//    }
//
//    public void setTimers(Timers timers) {
//        this.timers = timers;
//    }
}
