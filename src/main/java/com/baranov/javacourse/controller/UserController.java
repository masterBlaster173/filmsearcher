package com.baranov.javacourse.controller;

import com.baranov.javacourse.entity.Actor;
import com.baranov.javacourse.entity.Film;
import com.baranov.javacourse.repo.ActorRepo;
import com.baranov.javacourse.repo.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private FilmRepo filmRepo;
    @Autowired
    private ActorRepo actorRepo;

        @GetMapping("/") //стартовая страница
        public String greeting() {
            return "greeting";
        }

        @GetMapping("/add-film") // страница редактора

        public String findFilms(Map<String, Object> model) {
            Iterable<Film> films = filmRepo.findAll();
            model.put("films", films);

            Iterable<Actor> actors = actorRepo.findAll();  // для отображения списка актёров
            model.put("actors", actors);
            return "filmadder";
        }

        @PostMapping("/add-film") // страница редактора


        //внесение значений в БД
        public String addFilm(@RequestParam String title, @RequestParam String year, @RequestParam String filmDescrip, @RequestParam(defaultValue = "пусто") String mainActor, Map<String, Object> model){

            if (year.equals("")) {
                year = "неизвестен"; // в случае незаполнения формы, выдаётся "год неизвестен"
            }
            else if (filmDescrip.equals("")) {
                filmDescrip = "Описание отсутствует"; // в случае незаполнения формы, выдаётся "Описание отсутствует"
            }

            Film film = new Film(title, year, filmDescrip, mainActor);
            filmRepo.save(film);
            //чтение из БД
            Iterable<Film> films = filmRepo.findAll();
            model.put("films", films);

        return "filmadder";
        }

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

    @GetMapping("/add-actor") // страница редактора

    public String findActors(Map<String, Object> model) {     //
        Iterable<Actor> actors = actorRepo.findAll();
        model.put("actors", actors);
        return "actoradder";
    }

    @PostMapping("/add-actor")
    //внесение значений в БД
    public String addActor(@RequestParam String firstame, @RequestParam String lastname, @RequestParam String age,@RequestParam String bio, Map<String, Object> model){


           // age = "неизвестен"; // в случае незаполнения формы, выдаётся "год неизвестен"
            Actor actor = new Actor(firstame, lastname, age, bio);
            actorRepo.save(actor);
            //чтение из БД
            Iterable<Actor> actors = actorRepo.findAll();
            model.put("actors", actors);

            return "actoradder";

    }
}

