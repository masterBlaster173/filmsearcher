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

    private String genre;

    private Integer year;

    private String filmDescrip;

    public Film() {

    }

    public Film(String title, Integer year, String filmDescrip) {
        this.title = title;
        this.year = year;
        this.filmDescrip = filmDescrip;
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

    public Integer getYear() {
        if (year==null) {
            return 0;
        }
        else return year;
    }

    public String getFilmDescrip() {
        return filmDescrip;
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

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setFilmDescrip(String filmDescrip) {
        this.filmDescrip = filmDescrip;
    }
}