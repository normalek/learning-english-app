package com.learning.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_rec;
    @Column(name = "date", columnDefinition = "DATE")
    private String date;
    private Byte correct;
    private Byte incorrect;
    private Byte total;

    public Integer getId_rec() {
        return id_rec;
    }

    public void setId_rec(Integer id_rec) {
        this.id_rec = id_rec;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Byte getCorrect() {
        return correct;
    }

    public void setCorrect(Byte correct) {
        this.correct = correct;
    }

    public Byte getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(Byte incorrect) {
        this.incorrect = incorrect;
    }

    public Byte getTotal() {
        return total;
    }

    public void setTotal(Byte total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "id_rec: " + id_rec + ", date: " + date + ", correct: " + correct + ", incorrect: " + incorrect + ", total: " + total;
    }
}
