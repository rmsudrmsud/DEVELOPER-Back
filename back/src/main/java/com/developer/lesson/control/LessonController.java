package com.developer.lesson.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.service.LessonService;

@RestController
@RequestMapping("lesson/*")
public class LessonController {
	@Autowired
	private LessonService lservice;
//	
//	// [JW] 선택한 클래스에 대한 상세 정보
//	@GetMapping(value = {"{lessonSeq}"}, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
//	public ResponseEntity<?> detail(@PathVariable Long lessonSeq) throws FindException{
//		Lesson lesson = service.selectDetail(lessonSeq);
//		return new ResponseEntity<>(lesson, HttpStatus.OK);
//	}
//	
//	// [JW] 수업 등록
//	@PostMapping
//	public ResponseEntity<LessonRequestDTO> add(@RequestBody LessonRequestDTO dto) throws AddException, FindException{
//		service.addLesson(dto);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	@GetMapping(value = "getlesson3/{tutorId}" )
	   public ResponseEntity<?> getLessonByUser3(@PathVariable String tutorId) throws FindException{
	      List<LessonDTO> list = lservice.getLessonByUser3(tutorId);
	      return new ResponseEntity<>(list, HttpStatus.OK);      
	   }
}
