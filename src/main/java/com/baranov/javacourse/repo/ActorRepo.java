package com.baranov.javacourse.repo;


import com.baranov.javacourse.entity.Actor;
import org.springframework.data.repository.CrudRepository;


public interface ActorRepo extends CrudRepository<Actor, Long> {

}

