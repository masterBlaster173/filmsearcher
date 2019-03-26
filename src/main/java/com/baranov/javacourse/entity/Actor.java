package com.baranov.javacourse.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String actorName;

    private String gender;

    private String age;

    private String bio;

    public Actor() {

    }

    public Actor(String actorName, String genre, String age, String bio) {
        this.actorName = actorName;
        this.gender = genre;
        this.age = age;
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public String getActorName() {
        return actorName;
    }

    public String getGenre() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getBio() {
        return bio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setGenre(String genre) {
        this.gender = genre;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
