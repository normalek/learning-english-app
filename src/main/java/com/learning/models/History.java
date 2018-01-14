package com.learning.models;

import javax.persistence.*;

//@Component
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_rec;
    @Column(name = "date", columnDefinition = "DATE")
    private String date;
    private Byte right;
    private Byte wrong;
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

    public Byte getRight() {
        return right;
    }

    public void setRight(Byte right) {
        this.right = right;
    }

    public Byte getWrong() {
        return wrong;
    }

    public void setWrong(Byte wrong) {
        this.wrong = wrong;
    }

    public Byte getTotal() {
        return total;
    }

    public void setTotal(Byte total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "id_rec: " + id_rec + ", date: " + date + ", right: " + right + ", wrong: " + wrong + ", total: " + total;
    }
}
