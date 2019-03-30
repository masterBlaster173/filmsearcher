package com.baranov.javacourse.controller;

import com.baranov.javacourse.entity.Actor;
import com.baranov.javacourse.entity.Film;
import com.baranov.javacourse.repo.ActorRepo;
import com.baranov.javacourse.repo.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ServiceController {
    @Autowired
    private FilmRepo filmRepo;
    @Autowired
    private ActorRepo actorRepo;

    /*
     * Start page
     */
    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }
    @GetMapping("/add-film") // editor page
    public String findFilms(Map<String, Object> model) {
        //reading from database
        Iterable<Film> films = filmRepo.findAll();
        model.put("films", films);
        // to display a list of actors on "greeting" page
        Iterable<Actor> actors = actorRepo.findAll();
        model.put("actors", actors);
        return "filmadder";
    }

    /*
     * Adding movies to the database
     */
    @PostMapping("/add-film") // editor page
    public String addFilm(@RequestParam String title,
                          @RequestParam(defaultValue = "") String year,
                          @RequestParam(defaultValue = "отписание отсутствует") String filmDescrip,
                          @RequestParam(defaultValue = "пусто") String mainActor, Map<String, Object> model){

        //create new entity
        Film film = new Film(title, year, filmDescrip, mainActor);
        //match search
        Film filmFromDb = filmRepo.findFilmByTitleAndYear(film.getTitle(), film.getYear());
        if(filmFromDb != null ){
            model.put("error", "Такой фильм уже существует!");
        }
        else if(film.getTitle().equals("")){
            model.put("error", "Не задано название!");
        }else {
            filmRepo.save(film);
            Iterable<Film> films = filmRepo.findAll();
            model.put("films", films);
        }
        return "filmadder";
    }

    /*
     * Search movies in the database
     */
    @RequestMapping("/find-film")
    public String filmFilter(@RequestParam(required = false) String filter ,
                             @RequestParam(value="button", defaultValue = "0") String button,  Map<String, Object> model) {
        Iterable<Film> films;
        switch (button) {
            case "0":
                films = filmRepo.findAll();
                model.put("films", films);
                break;
            case "1":
                films = filmRepo.findByTitle(filter);
                model.put("films", films);
                break;
            case "2":
                films = filmRepo.findByYear(filter);
                model.put("films", films);
                break;
        }
        return "filmfinder";
    }

    /*
     * Adding actors in the database
     */
    @GetMapping("/add-actor")
    public String findActors(Map<String, Object> model) {
        //reading from database
        Iterable<Actor> actors = actorRepo.findAll();
        model.put("actors", actors);
        return "actoradder";
    }

    @PostMapping("/add-actor")
    public String addActor(@RequestParam String firstame,
                           @RequestParam String lastname,
                           @RequestParam String age,
                           @RequestParam String bio, Map<String, Object> model){
        Actor actor = new Actor(firstame, lastname, age, bio);
        actorRepo.save(actor);
        //reading from database
        Iterable<Actor> actors = actorRepo.findAll();
        model.put("actors", actors);
        return "actoradder";

    }

    /*
     * Delete movies from the database
     */
    @GetMapping("/del-film") // editor page
    public String findFilms2(Map<String, Object> model) {
        //reading from database
        Iterable<Film> films = filmRepo.findAll();
        model.put("films", films);

        return "filmdeleter";
    }

    @PostMapping("/del-film") // editor page
    public String delFilm(Film film, Map<String, Object> model){
        Film filmFromDb = filmRepo.findFilmById(film.getId());
        if(filmFromDb != null ) {
            filmRepo.delete(film);
        }
        else {
            model.put("error", "Нет вильма с таким id");
        }
        Iterable<Film> films = filmRepo.findAll();
        model.put("films", films);
        return "filmdeleter";
    }
}


