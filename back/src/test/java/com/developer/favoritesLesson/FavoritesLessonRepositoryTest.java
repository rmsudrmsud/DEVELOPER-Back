package com.developer.favoritesLesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.favoriteslesson.entity.FavoritesLesson;
import com.developer.favoriteslesson.repository.FavoritesLessonRepository;
import com.developer.lesson.entity.Lesson;

@SpringBootTest
public class FavoritesLessonRepositoryTest {

	@Autowired
	private FavoritesLessonRepository flRepository;
	
	@Test
	@DisplayName("SelectDetail() 테스트")
	void testSelectDetail() {
		Optional<FavoritesLesson> optL = flRepository.findById(1L);
		assertTrue(optL.isPresent());
		Long expected = 1L;
		assertEquals(expected, optL.get().getFavLesSeq());
	}
	
	@Test
	@DisplayName("insert() 테스트")
	void testSave() {
		FavoritesLesson fl = new FavoritesLesson();
		Lesson l = new Lesson();
		l.setLessonSeq(1L);
		fl.setFavLesSeq(2L);
		fl.setLesson(l);
		flRepository.save(fl);
	}


}
