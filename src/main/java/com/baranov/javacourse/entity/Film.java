package com.baranov.javacourse.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String genre; //reserved

    private String year;

    private String filmDescrip;

    private String mainActor;

    public Film() {

    }

    public Film(String title, String year, String filmDescrip,String mainActor) {
        this.mainActor = mainActor;
        this.title = title;
        this.year = year;
        this.filmDescrip = filmDescrip;
    }

    public String getMainActor() {
        return mainActor;
    }
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
         return year;
    }
    public String getFilmDescrip() {
        return filmDescrip;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public void setFilmDescrip(String filmDescrip) {
        this.filmDescrip = filmDescrip;
    }
}