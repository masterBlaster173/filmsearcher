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
    public String addFilm(@RequestParam String title, @RequestParam String year, @RequestParam String filmDescrip, @RequestParam(defaultValue = "пусто") String mainActor, Map<String, Object> model){
        // in case of non-completion of the form, issued "год неизвестен"
        if (year.equals("")) {
            year = "неизвестен";
        }
        // in case of non-completion of the form, issued "Описание отсутствует"
        else if (filmDescrip.equals("")) {
            filmDescrip = "Описание отсутствует";
        }
        Film film = new Film(title, year, filmDescrip, mainActor);
        Film filmFromDb = filmRepo.findFilmByTitleAndYear(film.getTitle(), film.getYear());
        if(filmFromDb != null){
            model.put("error", "Такой фильм уже существует!");
        } else {
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
    public String filmFilter(@RequestParam(required = false) String filter , @RequestParam(value="button", defaultValue = "0") String button,  Map<String, Object> model) {
        Iterable<Film> films;
        String b = button;

        if (b.equals("0")) {
            films = filmRepo.findAll();
            model.put("films", films);
        }
        else if (b.equals("1")) {
            films = filmRepo.findByTitle(filter);
            model.put("films", films);
        }
        else if (b.equals("2")) {
            films = filmRepo.findByYear(filter);
            model.put("films", films);
        }
        return "filmfinder";
    }

    /*
     * Adding actors in the database
     */
    @GetMapping("/add-actor")
    public String findActors(Map<String, Object> model) {     //
        Iterable<Actor> actors = actorRepo.findAll();
        model.put("actors", actors);
        return "actoradder";
    }

    @PostMapping("/add-actor")
    public String addActor(@RequestParam String firstame, @RequestParam String lastname, @RequestParam String age,@RequestParam String bio, Map<String, Object> model){
        Actor actor = new Actor(firstame, lastname, age, bio);
        actorRepo.save(actor);
        //чтение из БД
        Iterable<Actor> actors = actorRepo.findAll();
        model.put("actors", actors);
        return "actoradder";

    }
}

