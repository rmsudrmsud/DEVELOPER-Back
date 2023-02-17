package com.developer.favoriteslesson.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.favoriteslesson.entity.FavoritesLesson;
import com.developer.favoriteslesson.service.FavoritesLessonService;

@RestController
@RequestMapping("favoriteslesson/*")
public class FavoritesLessonController {
	@Autowired
	FavoritesLessonService service;
	
	//[JW] 수업 즐겨찾기 추가 
	@PostMapping
	public ResponseEntity<?> add(@RequestBody FavoritesLesson favoritesLesson) throws AddException, FindException{
		service.addFavLesson(favoritesLesson);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//[JW] 수업 즐겨찾기 삭제
	@DeleteMapping(value = "{favLesSeq}")
	public ResponseEntity<?> del(@PathVariable Long favLesSeq) throws RemoveException, FindException{
		service.delFavLesson(favLesSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
