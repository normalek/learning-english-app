package com.learning.models;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_rec;
    private String word;
    private String explanation;
    private String current_word;
//    @OneToOne(mappedBy = "dictionaryItem")
//    private Timers timers;

    public Integer getId_rec() {
        return id_rec;
    }

    public void setId_rec(Integer id_rec) {
        this.id_rec = id_rec;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCurrent_word() {
        return current_word;
    }

    public void setCurrent_word(String current_word) {
        this.current_word = current_word;
    }

//    public Timers getTimers() {
//        return timers;
//    }
//
//    public void setTimers(Timers timers) {
//        this.timers = timers;
//    }
}

