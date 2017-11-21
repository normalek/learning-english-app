package com.learning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Dictionary {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_rec;

    private String word;

    private String explanation;

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
    
    
}

