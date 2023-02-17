package com.developer.favoriteslesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.favoriteslesson.entity.FavoritesLesson;
import com.developer.favoriteslesson.repository.FavoritesLessonRepository;

@Service
public class FavoritesLessonService {
	@Autowired
	FavoritesLessonRepository flRepository;
	
	//[JW] 수업 즐겨찾기 추가 
	public void addFavLesson(FavoritesLesson favoritesLesson) throws FindException{
		flRepository.save(favoritesLesson);
	}
	
	//[JW] 수업 즐겨찾기 삭제 
	public void delFavLesson(Long favLesSeq) throws FindException{
		flRepository.deleteById(favLesSeq);
	}
	
}

