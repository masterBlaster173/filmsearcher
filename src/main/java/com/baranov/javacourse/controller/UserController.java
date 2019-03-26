package com.baranov.javacourse.controller;


import com.baranov.javacourse.entity.Film;
import com.baranov.javacourse.repo.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private FilmRepo filmRepo;

    @GetMapping("/") //стартовая страница
    public String greeting() {
        return "greeting";
    }

        @GetMapping("/add-film") // страница редактора

        public String findFilms(Map<String, Object> model) {     //
            Iterable<Film> films = filmRepo.findAll();
            model.put("films", films);
            return "filmadder";
        }

        @PostMapping("/add-film")

        //внесение значений в БД
        public String addFilm(@RequestParam String title, @RequestParam Integer year, @RequestParam String filmDescrip, Map<String, Object> model){

            Film film = new Film(title, year, filmDescrip);
            filmRepo.save(film);
            //чтение из БД
            Iterable<Film> films = filmRepo.findAll();
            model.put("films", films);


        return "filmadder";
        }
    }


