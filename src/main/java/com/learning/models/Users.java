package com.learning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_rec;
    private String username;
    private String password;
    private byte enabled;
    private byte words;
    private byte repetition_period;

    public Integer getId_rec() {
        return id_rec;
    }

    public void setId_rec(Integer id_rec) {
        this.id_rec = id_rec;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public byte getWords() {
        return words;
    }

    public void setWords(byte words) {
        this.words = words;
    }

    public byte getRepetition_period() {
        return repetition_period;
    }

    public void setRepetition_period(byte repetition_period) {
        this.repetition_period = repetition_period;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id_rec=" + id_rec +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", words=" + words +
                ", repetition_period=" + repetition_period +
                '}';
    }
}
