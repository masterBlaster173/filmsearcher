package com.baranov.javacourse.repo;


import com.baranov.javacourse.entity.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepo extends CrudRepository<Film, Long> {

    List<Film> findByTitle(String title);

    List<Film> findByYear(String year);

}

