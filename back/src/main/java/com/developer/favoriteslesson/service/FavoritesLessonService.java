package com.developer.favoriteslesson.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.favoriteslesson.entity.FavoritesLesson;
import com.developer.favoriteslesson.repository.FavoritesLessonRepository;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;

@Service
public class FavoritesLessonService {
	@Autowired
	FavoritesLessonRepository flRepository;
	@Autowired
	private LessonRepository lRepository;
	
	//[JW] 나의 수업 즐겨찾기 목록 확인 
	public List<Object[]> listFavLesson(String userId) throws FindException{
		List<Object[]> list = flRepository.listFavLesson(userId);
		return list;
	}
	
	//[JW] 수업 즐겨찾기 추가 
	public void addFavLesson(FavoritesLesson favoritesLesson, Long lessonSeq) throws AddException{
		Optional<Lesson> l = lRepository.findById(lessonSeq);
		Lesson lesson = l.get();
		favoritesLesson.setLesson(lesson);
		flRepository.save(favoritesLesson);
	}
	
	//[JW] 수업 즐겨찾기 삭제 
	public void delFavLesson(Long favLesSeq) throws RemoveException{
		flRepository.deleteById(favLesSeq);
	}
	
}

