package com.baranov.javacourse.repo;

import com.baranov.javacourse.controller.UserController;
import com.baranov.javacourse.entity.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepo extends CrudRepository<Film, Long> {

//    List<UserController> findByTitle(String title);
//
//    List<UserController> findByYear(Integer year);
}

