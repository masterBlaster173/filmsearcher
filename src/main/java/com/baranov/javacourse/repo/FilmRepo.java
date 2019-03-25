package com.baranov.javacourse.repo;

import com.baranov.javacourse.controller.UserController;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepo extends CrudRepository<UserController, Long> {

    List<UserController> findByTitle(String title);
}
