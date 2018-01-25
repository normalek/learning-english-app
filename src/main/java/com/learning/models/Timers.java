package com.learning.models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity // This tells Hibernate to make a table out of this class
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "find_words", procedureName = "find_words",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "words_amount", type = Integer.class),
//                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = ArrayList.class)
                }
        )
})
public class Timers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_rec;
    private Integer counter;
    @Column(name = "date_learned", columnDefinition = "DATE")
    private String date_learned;
    private Integer need_repeat;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_word")
    private Dictionary dictionaryItem;
    @ManyToOne
    @JoinColumn(name = "id_gradation")
    private Gradations gradationItem;

    public Integer getId_rec() {
        return id_rec;
    }

    public void setId_rec(Integer id_rec) {
        this.id_rec = id_rec;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getNeed_repeat() {
        return need_repeat;
    }

    public void setNeed_repeat(Integer need_repeat) {
        this.need_repeat = need_repeat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dictionary getDictionaryItem() {
        return dictionaryItem;
    }

    public void setDictionaryItem(Dictionary dictionaryItem) {
        this.dictionaryItem = dictionaryItem;
    }

    public Gradations getGradationItem() {
        return gradationItem;
    }

    public void setGradationItem(Gradations gradationItem) {
        this.gradationItem = gradationItem;
    }

    public String getDate_learned() {
        return date_learned;
    }

    public void setDate_learned(String date_learned) {
        this.date_learned = date_learned;
    }
}
