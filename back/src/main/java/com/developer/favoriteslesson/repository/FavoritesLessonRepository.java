package com.developer.favoriteslesson.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.favoriteslesson.entity.FavoritesLesson;

public interface FavoritesLessonRepository extends CrudRepository<FavoritesLesson, Long> {

}
