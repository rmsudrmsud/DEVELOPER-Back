package com.developer.lesson.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.lesson.entity.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
